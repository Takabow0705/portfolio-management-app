package app.component.external.calculator.service;

import com.google.common.flogger.FluentLogger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.grpc.ManagedChannel;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationBatchServiceGrpc;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import io.grpc.util.Status;
import io.grpc.util.StatusMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
public class PortfolioEvaluationBatchServiceImpl implements PortfolioEvaluationBatchService{
    @Autowired
    @Qualifier("calculator")
    private ManagedChannel managedChannel;

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Override
    public PortfolioEvaluationResponse executeForceEvaluation(PortfolioEvaluationRequest req) {
        PortfolioEvaluationBatchServiceGrpc.PortfolioEvaluationBatchServiceBlockingStub stub = PortfolioEvaluationBatchServiceGrpc.newBlockingStub(managedChannel);
        PortfolioEvaluationResponse response = stub.executeForceEvaluation(req);
        return response;
    }

    @Override
    public PortfolioEvaluationResponse executeReviseEvaluation(PortfolioEvaluationRequest req) {
        PortfolioEvaluationBatchServiceGrpc.PortfolioEvaluationBatchServiceBlockingStub stub = PortfolioEvaluationBatchServiceGrpc.newBlockingStub(managedChannel);
        PortfolioEvaluationResponse response = stub.executeReviseEvaluation(req);
        return response;
    }

    @Override
    public PortfolioEvaluationResponse executeRegularEvaluation(PortfolioEvaluationRequest req) {
        PortfolioEvaluationBatchServiceGrpc.PortfolioEvaluationBatchServiceBlockingStub stub = PortfolioEvaluationBatchServiceGrpc.newBlockingStub(managedChannel);
        PortfolioEvaluationResponse response = stub.executeRegularEvaluation(req);
        return response;
    }

     PortfolioEvaluationResponse fallback(PortfolioEvaluationRequest request, Exception e){
        logger.atWarning().log("Request was not executed. Reason: [%s]", e.getCause());
        PortfolioEvaluationResponse res = PortfolioEvaluationResponse.newBuilder()
                .setStatusMsg(StatusMsg.newBuilder().setStatus(Status.ERROR).setDetail("Calculatorサーバへの接続不可").build())
                .build();
        return res;
    }
}
