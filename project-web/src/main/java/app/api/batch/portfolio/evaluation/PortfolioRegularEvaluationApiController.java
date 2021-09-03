package app.api.batch.portfolio.evaluation;

import app.commons.api.response.PortfolioEvaluationApiResponse;
import app.component.domain.portfolio.dto.PortfolioEvaluationParam;
import app.component.external.calculator.service.PortfolioEvaluationBatchService;
import com.google.common.flogger.FluentLogger;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import io.grpc.util.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "標準評価計算の実行EndPoint"
            , description = "この計算は指定期間内の約定データから移動平均法によって各営業日の取得簿価を計算し、各営業日の株式終値から評価損益を計算する。計算自体は計算サーバに登録され、非同期に実行される。したがって、レスポンスの返却 = 計算終了ではない。")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "評価計算ジョブの登録に成功したこと")
                    ,@ApiResponse(responseCode = "503", description = "計算サーバが利用不可、もしくはWebサーバの内部処理に問題発生")
            }
    )
    public ResponseEntity<PortfolioEvaluationApiResponse> executeRegularEvaluation(@RequestBody PortfolioEvaluationParam param){
        logger.atInfo().log("Receive Calculation Request param = %s, Type: Regular", param.toString());
        PortfolioEvaluationRequest req = PortfolioEvaluationRequest
                .newBuilder()
                .setPortfolioId(param.getPortfolioId())
                .setStartDate(param.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .setEndDate(param.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();

        PortfolioEvaluationResponse res = this.portfolioEvaluationBatchService.executeRegularEvaluation(req);

        HttpHeaders headers = new HttpHeaders();

        if (res == null || res.getStatusMsg().getStatus() == Status.ERROR || res.getStatusMsg().getStatus() == Status.UNRECOGNIZED){
            ResponseEntity<PortfolioEvaluationApiResponse> response = new ResponseEntity<>(PortfolioEvaluationApiResponse.of(res), headers, HttpStatus.SERVICE_UNAVAILABLE);
            return response;
        }
        ResponseEntity<PortfolioEvaluationApiResponse> response = new ResponseEntity<>(PortfolioEvaluationApiResponse.of(res), headers, HttpStatus.OK);
        return response;
    }
}
