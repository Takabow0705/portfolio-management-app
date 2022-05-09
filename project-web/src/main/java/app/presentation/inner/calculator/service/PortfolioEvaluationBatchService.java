package app.presentation.inner.calculator.service;

import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;
import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationResponse;

public interface PortfolioEvaluationBatchService {

    public abstract PortfolioEvaluationResponse executeForceEvaluation(PortfolioEvaluationRequest req);
    public abstract PortfolioEvaluationResponse executeReviseEvaluation(PortfolioEvaluationRequest req);
    public abstract PortfolioEvaluationResponse executeRegularEvaluation(PortfolioEvaluationRequest req);

}
