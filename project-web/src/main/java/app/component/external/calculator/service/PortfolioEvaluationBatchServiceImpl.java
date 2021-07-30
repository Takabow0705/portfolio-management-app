package app.component.external.calculator.service;

import io.grpc.ManagedChannel;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationBatchServiceGrpc;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PortfolioEvaluationBatchServiceImpl implements PortfolioEvaluationBatchService{
    @Autowired
    @Qualifier("calculator")
    private ManagedChannel managedChannel;

    @Override
    public PortfolioEvaluationResponse executeForceEvaluation(PortfolioEvaluationRequest req) {
        PortfolioEvaluationBatchServiceGrpc.PortfolioEvaluationBatchServiceBlockingStub stub = PortfolioEvaluationBatchServiceGrpc.newBlockingStub(managedChannel);
        PortfolioEvaluationResponse response = stub.executeForceEvaluation(req);
        return response;
    }

    @Override
    public PortfolioEvaluationResponse executeReviseEvaluation(PortfolioEvaluationRequest req) {
        return null;
    }

    @Override
    public PortfolioEvaluationResponse executeRegularEvaluation(PortfolioEvaluationRequest req) {
        return null;
    }
}
