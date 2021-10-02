package app.domain.api.response;

import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import io.grpc.util.Status;
import io.swagger.v3.oas.annotations.media.Schema;

public class PortfolioEvaluationApiResponse {
    @Schema(description = "計算サーバから返されたResponse")
    private final Status status;
    @Schema(description = "計算サーバからの詳細メッセージ", nullable = true)
    private final String statusMessage;

    public PortfolioEvaluationApiResponse(Status status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    /**
     * 計算サーバからのResponseをweb側のResponseに変換する。
     * @param grpcResponse
     * @return
     */
    public static PortfolioEvaluationApiResponse of(PortfolioEvaluationResponse grpcResponse){
        if (grpcResponse == null){
            return new PortfolioEvaluationApiResponse(
                    Status.ERROR
                    ,"Response is null"
            );
        }
        return new PortfolioEvaluationApiResponse(
                grpcResponse.getStatusMsg().getStatus()
                ,grpcResponse.getStatusMsg().getDetail()
        );
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
