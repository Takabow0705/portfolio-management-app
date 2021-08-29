package project.calculator.domain.batch.porfolio.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.calculator.domain.batch.porfolio.DailyPosition;
import project.calculator.domain.batch.porfolio.UnitPriceCalculator;
import project.calculator.domain.calendar.BusinessDays;
import project.infra.rdb.stockexecution.entity.StockExecution;
import project.infra.rdb.stockportfolioevaluation.StockPortfolioEvaluation;
import project.infra.rdb.strockpricetimeseries.StockPrice;
import project.infra.rdb.strockpricetimeseries.StockPriceBase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 移動平均法によって平均取得単価を計算するクラス
 */
public class MovingAverageUnitPriceCalculator implements UnitPriceCalculator<List<StockPortfolioEvaluation>> {

    private final List<StockExecution> execution;
    private final List<StockPrice> stockPrice;
    private final BusinessDays businessDays;

    private static final Logger logger = LoggerFactory.getLogger(MovingAverageUnitPriceCalculator.class);

    private MovingAverageUnitPriceCalculator(List<StockExecution> execution, List<StockPrice> stockPrice, BusinessDays businessDays) {
        this.execution = execution;
        this.stockPrice = stockPrice;
        this.businessDays = businessDays;
    }

    /**
     * 平均取得単価の計算を実行する計算機インスタンスを返す。
     * 計算対象の約定データは株式コード毎に設定される想定になっているため、内部でその確認を行う。
     * @param execution
     * @param stockPrice
     * @return
     */
    public static UnitPriceCalculator<List<StockPortfolioEvaluation>> generate(List<StockExecution> execution, List<StockPrice> stockPrice, BusinessDays businessDays){
        Set<String> stockCodes = execution.stream().map(StockExecution::getStockCode).collect(Collectors.toSet());
        if (stockPrice == null){
            throw new IllegalStateException(String.format("StockPrice is null. Expected -> %s", stockCodes));
        }
        Set<String> marketDateCodes = stockPrice.stream().map(StockPriceBase::getStockCode).collect(Collectors.toSet());
        if(stockCodes.isEmpty() || stockCodes.size() != 1){
            throw new IllegalArgumentException(String.format("Calculation target must have only 1 stock code. Actual Num -> %d", stockPrice.size()));
        }
        if(marketDateCodes.isEmpty() || marketDateCodes.size() != 1){
            throw new IllegalArgumentException(String.format("StockPrice must have only 1 stock code. Actual Num -> %d", marketDateCodes.size()));
        }
        logger.info(String.format("Generate Calculator : StockCode: %s", stockCodes.toString()));
        return new MovingAverageUnitPriceCalculator(execution,stockPrice,businessDays);
    }

    @Override
    public List<StockPortfolioEvaluation> calculate() {

        // 初期値
        BigDecimal prevAmount = BigDecimal.ZERO;
        BigDecimal prevBookValue = BigDecimal.ZERO;
        BigDecimal prevTotalValue = BigDecimal.ZERO;
        // 対象基準日からDailyPositionを生成
        List<DailyPosition> dailyPositions = this.businessDays.getBusinessDays().stream()
                .map(baseDate -> DailyPosition.aggregateDailyExecution(baseDate, execution))
                .collect(Collectors.toList());

        List<StockPortfolioEvaluation> tmpResult = new ArrayList<>();
        for(LocalDate businessday : this.businessDays.getBusinessDays()){
            // businessdayのポジションを取得。
            DailyPosition tPosition = dailyPositions.stream().filter(p -> p.getBaseDate().equals(businessday)).findFirst().orElseThrow(IllegalArgumentException::new);

            // T時点までの買ポジションの合計を算出する。
            BigDecimal totalAmount = prevAmount.add(tPosition.getLongAmount());
            // ポジションが存在するならT-1 時点までの平均取得単価からT時点までの平均取得単価を計算する。
            if(!(totalAmount.signum() == 0)){
                prevBookValue = (prevTotalValue.add(tPosition.calculateLongValue())).divide(totalAmount, 10 , RoundingMode.DOWN);
            }
            // T-1 までの残高に T のポジション残高を加算する。
            prevAmount = prevAmount.add(tPosition.calculateTotalAmount());
            prevTotalValue = prevBookValue.multiply(prevAmount);

            // マーケットデータを取得する。
            StockPrice stockPrice = this.retrieveStockPrice(businessday);

            BigDecimal currentValue = null;
            BigDecimal currentPl = null;
            LocalDate evaluationDateBaseDate = null;

            if (stockPrice != null){
                currentValue = stockPrice.getClosePrice();
                currentPl = currentValue.subtract(prevBookValue);
                evaluationDateBaseDate = stockPrice.getBaseDate();
            }

            // 必要なエンティティを構築する。
            StockPortfolioEvaluation evaluation = new StockPortfolioEvaluation();
            evaluation.setStockPortfolioId(tPosition.getStockPortfolioId());
            evaluation.setStockCode(tPosition.getStockCode());
            evaluation.setBaseDate(businessday);
            evaluation.setCurrentValue(currentValue);
            evaluation.setBookValue(prevBookValue);
            evaluation.setAmount(prevAmount);
            evaluation.setCurrentPl(currentPl);
            evaluation.setLockOut(stockPrice == null || !stockPrice.getBaseDate().equals(businessday));
            evaluation.setCurrencyCode(tPosition.getCurrencyCode());
            evaluation.setEvaluationDateBaseDate(evaluationDateBaseDate);
            evaluation.setCreateUser("Calculator_" + Thread.currentThread().getName());
            evaluation.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            evaluation.setUpdateUser("Calculator_" + Thread.currentThread().getName());
            evaluation.setUpdateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            tmpResult.add(evaluation);
            logger.debug(String.format("Finished Evaluation : %s", evaluation));
            logger.debug(String.format("MarketDataBaseDate : %s", evaluation.getEvaluationDateBaseDate()));
        }
        List<StockPortfolioEvaluation> result = tmpResult.stream().filter(s -> s.getAmount().signum() != 0).collect(Collectors.toList());
        return result;
    }

    /**
     * baseDateのStockPriceを取得する。
     * 該当のデータがない場合は、baseDate以前の最新のStockPriceで横置きする。
     * 横置きも不可の場合はnullを返す。
     * @param baseDate
     * @return
     */
    private StockPrice retrieveStockPrice(LocalDate baseDate){
        LocalDate currentDate = this.stockPrice.stream()
                .filter(s -> s.getBaseDate().isBefore(baseDate.plusDays(1)))
                .map(StockPriceBase::getBaseDate)
                .max(LocalDate::compareTo)
                .orElse(null);
        StockPrice stockPrice = this.stockPrice.stream()
                .filter(s -> s.getBaseDate().equals(currentDate))
                .findFirst().orElse(null);
        return stockPrice;
    }
}
