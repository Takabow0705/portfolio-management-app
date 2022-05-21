package app.presentation.api.execution;

import app.domain.auth.UserAuthInfo;
import app.domain.execution.StockExecutionInquiryDto;
import app.domain.execution.StockExecutionOutputCsv;
import app.domain.portfolio.StockExecutionSearchParam;
import app.usecase.portfolio.StockExecutionManagementService;
import com.google.common.flogger.FluentLogger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/app/portfolio/stock-execution")
public class StockExecutionInquiryApiController {

    private final StockExecutionManagementService stockExecutionManagementService;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public StockExecutionInquiryApiController(StockExecutionManagementService stockExecutionManagementService){
        this.stockExecutionManagementService = stockExecutionManagementService;
    }

    @PostMapping(path = "download")
    public ResponseEntity<String> download(@AuthenticationPrincipal UserAuthInfo session
            ,@RequestBody StockExecutionSearchParam param) {
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

    @GetMapping
    public ResponseEntity<Page<StockExecutionInquiryDto>> search(
              @RequestParam(value = "startDate", required = false) LocalDate startDate
              ,@RequestParam(value = "endDate", required = false) LocalDate endDate
              ,@RequestParam(value = "size") int size
              ,@RequestParam(value = "page", defaultValue = "0") int page
              ,@AuthenticationPrincipal UserAuthInfo session){
        if (session == null) {
            throw new SessionAuthenticationException("Session Info is null");
        }

        StockExecutionSearchParam searchParam = new StockExecutionSearchParam(startDate, endDate, session.getUserId(), size, page);
        logger.atInfo().log("Receive Execution Inquiry Request. : %s", searchParam.toString());
        Page<StockExecutionInquiryDto> result = this.stockExecutionManagementService.search(searchParam);
        // Responseを組み立てる
        HttpHeaders headers = new HttpHeaders();;
        return new ResponseEntity<>(result ,headers, HttpStatus.OK);
    }
}
