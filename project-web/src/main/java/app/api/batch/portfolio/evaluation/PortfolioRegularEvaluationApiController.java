package app.api.batch.portfolio.evaluation;

import app.component.domain.portfolio.dto.PortfolioEvaluationParam;
import app.component.external.calculator.service.PortfolioEvaluationBatchService;
import com.google.common.flogger.FluentLogger;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

/**
 * 標準評価計算の実行EndPoint
 */
@RestController
@RequestMapping("/api/v1/batch/portfolio/evaluation/")
public class PortfolioRegularEvaluationApiController {

    private PortfolioEvaluationBatchService portfolioEvaluationBatchService;

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public PortfolioRegularEvaluationApiController(PortfolioEvaluationBatchService portfolioEvaluationBatchService){
        this.portfolioEvaluationBatchService = portfolioEvaluationBatchService;
    }

    @PostMapping("execute-regular")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> executeRegularEvaluation(@RequestBody PortfolioEvaluationParam param){
        logger.atInfo().log("Receive Calculation Request param = %s, Type: Regular", param.toString());
        PortfolioEvaluationRequest req = PortfolioEvaluationRequest
                .newBuilder()
                .setPortfolioId(param.getPortfolioId())
                .setStartDate(param.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .setEndDate(param.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();

        PortfolioEvaluationResponse res = this.portfolioEvaluationBatchService.executeRegularEvaluation(req);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = new ResponseEntity<String>(res.toString(), headers, HttpStatus.OK);
        return response;
    }
}
