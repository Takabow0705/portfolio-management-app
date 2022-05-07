package app.presentation.api.execution;

import app.domain.auth.UserAuthInfo;
import app.domain.portfolio.CsvUploadResult;
import app.domain.portfolio.StockExectionCsvDto;
import app.usecase.portfolio.StockExecutionManagementService;
import com.google.common.flogger.FluentLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app/portfolio/stock-execution")
public class StockExecutionRegistrationApiController {

    private StockExecutionManagementService stockExecutionManagementService;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public StockExecutionRegistrationApiController(StockExecutionManagementService stockExecutionManagementService){
        this.stockExecutionManagementService = stockExecutionManagementService;
    }

    @PostMapping(path = "upload")
    public ResponseEntity<CsvUploadResult> upload(StockExectionCsvDto dto, @AuthenticationPrincipal UserAuthInfo session){
        if (session == null) {
            throw new SessionAuthenticationException("Session Info is null");
        }
        logger.atInfo().log("Upload File %s", dto.getMultipartFile().getName());
        CsvUploadResult csvUploadResult = this.stockExecutionManagementService.uploadCsv(dto, session);
        return csvUploadResult.convertToHttpResponse();
    }
}
