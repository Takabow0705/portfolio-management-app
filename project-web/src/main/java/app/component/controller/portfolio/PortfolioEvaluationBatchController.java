package app.component.controller.portfolio;

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

@RestController
@RequestMapping("/api/v1/batch/portfolio/evaluation/")
public class PortfolioEvaluationBatchController {

    private PortfolioEvaluationBatchService portfolioEvaluationBatchService;

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public PortfolioEvaluationBatchController(PortfolioEvaluationBatchService portfolioEvaluationBatchService){
        this.portfolioEvaluationBatchService = portfolioEvaluationBatchService;
    }

    @PostMapping("execute-regular")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> executeRegularEvaluation(@RequestBody PortfolioEvaluationParam param){
        logger.atInfo().log("Receive Calculation Request param = %s", param.toString());
        PortfolioEvaluationRequest req = PortfolioEvaluationRequest
                .newBuilder()
                .setPortfolioId(param.getPortfolioId())
                .setStartDate(param.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .setEndDate(param.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();

        PortfolioEvaluationResponse res = this.portfolioEvaluationBatchService.executeRegularEvaluation(req);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = new ResponseEntity<String>(res.getStatusMsg().getStatus().toString(), headers, HttpStatus.OK);
        return response;
    }

    @PostMapping(path = "execute-force")
    public String executeForceEvaluation(@ModelAttribute("param") PortfolioEvaluationParam param){
        return "";
    }

    @PostMapping(path = "execute-revise")
    public String executeReviseEvaluation(@ModelAttribute("param") PortfolioEvaluationParam param){
        return "";
    }

}
