package app.component.controller.portfolio;

import app.component.domain.portfolio.dto.PortfolioEvaluationParam;
import app.component.external.calculator.service.PortfolioEvaluationBatchService;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/app/batch/portfolio/evaluation")
public class PortfolioEvaluationBatchController {

    private PortfolioEvaluationBatchService portfolioEvaluationBatchService;

    public PortfolioEvaluationBatchController(PortfolioEvaluationBatchService portfolioEvaluationBatchService){
        this.portfolioEvaluationBatchService = portfolioEvaluationBatchService;
    }

    @PostMapping(path = "execute-regular")
    public ResponseEntity<String> executeRegularEvaluation(@ModelAttribute("param") PortfolioEvaluationParam param){
        PortfolioEvaluationRequest req = PortfolioEvaluationRequest
                .newBuilder()
                .setPortfolioId(param.getPortfolioId())
                .setStartDate(param.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .setEndDate(param.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
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
