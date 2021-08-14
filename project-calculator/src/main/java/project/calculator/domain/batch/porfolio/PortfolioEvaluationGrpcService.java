package project.calculator.domain.batch.porfolio;

import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationBatchServiceGrpc;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import io.grpc.stub.StreamObserver;
import io.grpc.util.Status;
import io.grpc.util.StatusMsg;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class PortfolioEvaluationGrpcService extends PortfolioEvaluationBatchServiceGrpc.PortfolioEvaluationBatchServiceImplBase {

    private final PortfolioEvaluationService portfolioEvaluationService;

    private static final Logger logger = LoggerFactory.getLogger(PortfolioEvaluationGrpcService.class);

    PortfolioEvaluationGrpcService(PortfolioEvaluationService portfolioEvaluationService){
        this.portfolioEvaluationService = portfolioEvaluationService;
    }

    /**
     * 再評価計算の実行メソッド
     *
     */
    @Override
    public void executeForceEvaluation(PortfolioEvaluationRequest request,
                                       StreamObserver<PortfolioEvaluationResponse> responseObserver) {

    }

    /**
     */
    @Override
    public void executeReviseEvaluation(PortfolioEvaluationRequest request,
                                        StreamObserver<PortfolioEvaluationResponse> responseObserver) {
    }

    /**
     */
    @Override
    public void executeRegularEvaluation(PortfolioEvaluationRequest request,
                                         StreamObserver<PortfolioEvaluationResponse> responseObserver) {
        this.portfolioEvaluationService.executeRegularEvaluation(request);
        logger.info(String.format("Registered Regular Evaluation Job. Param: %s", request.toString()));
        StatusMsg statusMsg = StatusMsg.newBuilder().setStatus(Status.OK).build();
        PortfolioEvaluationResponse response = PortfolioEvaluationResponse.newBuilder().setStatusMsg(statusMsg).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
