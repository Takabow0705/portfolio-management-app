package app.component.domain.portfolio.service.impl;

import app.commons.entities.portfolio.StockPortfolio;
import app.commons.entities.portfolio.execution.StockExecution;
import app.commons.entities.user.UserAuthInfo;
import app.commons.file.csv.StockExecutionOutputCsv;
import app.commons.file.csv.StockExecutionRegistrationCsv;
import app.component.domain.portfolio.dto.CsvUploadResult;
import app.component.domain.portfolio.dto.StockExecutionDownloadParam;
import app.component.domain.portfolio.repository.StockExecutionRepository;
import app.component.domain.portfolio.repository.StockPortfolioRepository;
import app.component.domain.portfolio.service.StockExecutionManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.flogger.FluentLogger;
import io.netty.util.internal.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.infra.rdb.currencymaster.CurrencyMaster;
import project.infra.rdb.currencymaster.CurrencyMasterRepository;
import project.infra.rdb.stockmaster.StockMaster;
import project.infra.rdb.stockmaster.StockMasterRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockExecutionManagementServiceImpl implements StockExecutionManagementService {

    private final StockExecutionRepository stockExecutionRepository;

    private final StockPortfolioRepository stockPortfolioRepository;

    private final StockMasterRepository stockMasterRepository;

    private final CurrencyMasterRepository currencyMasterRepository;

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public StockExecutionManagementServiceImpl(StockExecutionRepository stockExecutionRepository
    ,StockPortfolioRepository stockPortfolioRepository
    ,StockMasterRepository stockMasterRepository
    ,CurrencyMasterRepository currencyMasterRepository){
        this.stockExecutionRepository = stockExecutionRepository;
        this.stockPortfolioRepository = stockPortfolioRepository;
        this.stockMasterRepository = stockMasterRepository;
        this.currencyMasterRepository = currencyMasterRepository;
    }

    @Override
    public String downloadCsv(StockExecutionDownloadParam param) {
        List<Long> userPortfolioIds = this.stockPortfolioRepository.findByUserId(param.getUserId()).stream().map(StockPortfolio::getId).collect(Collectors.toList());
        // 所持するポートフォリオが無いなら空で返す。
        if (userPortfolioIds.isEmpty()){
            return StringUtil.EMPTY_STRING;
        }

        // 抽出条件を構築
        Specification<StockExecution> specs = Specification.where(StockExecution.executionDateAfter(param.getFromDate()))
                .and(StockExecution.executionDateBefore(param.getToDate()))
                .and(StockExecution.isOwnUser(userPortfolioIds));
        List<StockExecution> executions = this.stockExecutionRepository.findAll(specs);
        List<StockExecutionOutputCsv> csvObject = executions.stream().map(StockExecutionOutputCsv::createFrom).collect(Collectors.toList());

        String result = StringUtil.EMPTY_STRING;
        try{
            CsvMapper mapper = new CsvMapper();
            mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS,true);
            CsvSchema schema = mapper.schemaFor(StockExecutionOutputCsv.class).withHeader();
            result = mapper.writer(schema).writeValueAsString(csvObject);
        }catch (JsonProcessingException e){
              logger.atWarning().log("Error Occured in JsonProcessing %s", e.getMessage());
        }finally {
            return result;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,isolation = Isolation.READ_COMMITTED
            ,rollbackFor = {IOException.class, SQLException.class})
    public CsvUploadResult uploadCsv(List<StockExecutionRegistrationCsv> csv, UserAuthInfo session) {
        CsvUploadResult result = new CsvUploadResult();
        // stock_portfolio_idがポートフォリオに存在するか
        // 存在しないIDを指定している場合は一律にエラーとする
        Set<Long> stockPortfolioIds = this.stockPortfolioRepository.findByUserId(session.getUserId()).stream().map(StockPortfolio::getId).collect(Collectors.toSet());
        Set<Long> stockPortfolioIdsInCsv = csv.stream().map(StockExecutionRegistrationCsv::getStockPortfolioId).collect(Collectors.toSet());
        for(Long csvId : stockPortfolioIdsInCsv) {
            if (!stockPortfolioIds.contains(csvId)) {
                result.registerErrorMesasge(String.format("File Contains Unknown stock_portfolio_id. target id : %d", csvId));
            }
        }
        // stock_codeが株式マスタに存在するかを判定
        Set<Long> stockCodesInCsv = csv.stream().map(StockExecutionRegistrationCsv::getStockCode).map(Long::valueOf).collect(Collectors.toSet());
        Set<Long> stockCodes = this.stockMasterRepository.findByStockCode(stockCodesInCsv).stream().map(StockMaster::getStockCode).collect(Collectors.toSet());
        for(Long stockCodeInCsv : stockCodesInCsv){
            if (!stockCodes.contains(stockCodeInCsv)){
                result.registerErrorMesasge(String.format("File Contains Unknown stock_code. target stock_code : %d", stockCodeInCsv));
            }
        }
        // currency_codeが通貨マスタに存在するかを判定
        Set<String> currencyCodesInCsv = csv.stream().map(StockExecutionRegistrationCsv::getCurrencyCode).collect(Collectors.toSet());
        Set<String> currencyCodes = this.currencyMasterRepository.findByCurrencyCode(currencyCodesInCsv).stream().map(CurrencyMaster::getCurrencyCode).collect(Collectors.toSet());
        for(String currencyCodeInCsv : currencyCodes){
            if(!currencyCodes.contains(currencyCodeInCsv)){
                result.registerErrorMesasge(String.format("File Contains Unknown currency_code. target currency_code : %s", currencyCodeInCsv));
            }
        }

        if (result.hasError()){
            result.setStatus(CsvUploadResult.UploadStatus.ERROR);
            return result;
        }
        // stock_executionのエンティティに変換
        List<StockExecution> stockExecutions = csv.stream().map(StockExecution::createFrom).collect(Collectors.toList());
        // updateUser createUserを追加する。
        for(StockExecution execution : stockExecutions){
            execution.setUpdateUser(session.getUsername());
            execution.setCreateUser(session.getUsername());
        }
        this.stockExecutionRepository.saveAll(stockExecutions);
        logger.atInfo().log("Registered %d Stock Executions", stockExecutions.size());
        result.setStatus(CsvUploadResult.UploadStatus.OK);
        return result;
    }
}
