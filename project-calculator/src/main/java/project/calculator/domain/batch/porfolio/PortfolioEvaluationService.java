package project.calculator.domain.batch.porfolio;

import io.grpc.finance.calculation.batch.portfolio.PortfolioEvaluationRequest;

public interface PortfolioEvaluationService {
    public abstract void executeRegularEvaluation(PortfolioEvaluationRequest request);
    public abstract void executeForceEvaluation(PortfolioEvaluationRequest request);
    public abstract void executeReviseEvaluation(PortfolioEvaluationRequest request);
}
