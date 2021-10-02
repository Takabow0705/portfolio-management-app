package app.presentation.controller.portfolio;

import app.domain.auth.UserAuthInfo;
import app.domain.execution.StockExecutionOutputCsv;
import app.domain.execution.StockExecutionRegistrationCsv;
import app.domain.portfolio.CsvUploadResult;
import app.domain.portfolio.StockExectionCsvDto;
import app.domain.portfolio.StockExecutionDownloadParam;
import app.usecase.portfolio.StockExecutionManagementService;
import com.github.mygreen.supercsv.io.CsvAnnotationBeanReader;
import com.google.common.flogger.FluentLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequestMapping("/management/portfolio/stock/execution")
public class StockExecutionRegisterController {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private final StockExecutionManagementService stockExecutionManagementService;

    public StockExecutionRegisterController(StockExecutionManagementService stockExecutionManagementService){
        this.stockExecutionManagementService = stockExecutionManagementService;
    }

    @GetMapping
    public String index(Model model){
        StockExectionCsvDto dto = new StockExectionCsvDto();
        StockExecutionDownloadParam param = new StockExecutionDownloadParam();
        model.addAttribute("stockExectionCsvDto", dto);
        model.addAttribute("stockExecutionDownloadParam", param);
        return "management/portfolio/stock/execution/index";
    }

    @PostMapping("upload")
    public String upload(@AuthenticationPrincipal UserAuthInfo session
            ,StockExectionCsvDto stockExectionCsvDto, Model model){
        if (session == null) {
            throw new SessionAuthenticationException("Session Info is null");
        }
        CsvUploadResult csvUploadResult = new CsvUploadResult();
        logger.atInfo().log("Upload File %s", stockExectionCsvDto.getMultipartFile().getName());

        CsvAnnotationBeanReader<StockExecutionRegistrationCsv> reader = null;
        try(InputStream in = stockExectionCsvDto.getMultipartFile().getInputStream()){
            reader = new CsvAnnotationBeanReader<StockExecutionRegistrationCsv>(StockExecutionRegistrationCsv.class
                    ,new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()))
                    , CsvPreference.STANDARD_PREFERENCE);
            List<StockExecutionRegistrationCsv> csv = reader.readAll();
            csvUploadResult.merge(this.stockExecutionManagementService.uploadCsv(csv, session));
        }catch (IOException e){
            logger.atSevere().log("IO Error: %s", e.getMessage());
        }catch (SuperCsvException e){
            csvUploadResult.registerErrorMesasge(reader.getErrorMessages());
            csvUploadResult.setStatus(CsvUploadResult.UploadStatus.ERROR);
            logger.atWarning().log("CSV Parse Error. Cause -> %s", csvUploadResult.getErrorMessages());
        }finally {
            StockExectionCsvDto dto = new StockExectionCsvDto();
            StockExecutionDownloadParam param = new StockExecutionDownloadParam();
            model.addAttribute("stockExectionCsvDto", dto);
            model.addAttribute("stockExecutionDownloadParam", param);
        }
        return "management/portfolio/stock/execution/index";
    }

    @PostMapping(path = "download")
    public ResponseEntity<String> download(@AuthenticationPrincipal UserAuthInfo session
            , @ModelAttribute StockExecutionDownloadParam param
            , Model model) {
        if (session == null) {
            throw new SessionAuthenticationException("Session Info is null");
        }
        param.setUserId(session.getUserId());
        logger.atInfo().log("Download File %s", param.toString());

        // Responseを組み立てる
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("filename"
                , StockExecutionOutputCsv.createFilename(
                        param.getFromDate()
                        , param.getToDate()));
        String csv = this.stockExecutionManagementService.downloadCsv(param);
        return new ResponseEntity<>(csv ,headers, HttpStatus.OK);
    }
}
