package project.infra.rdb.stockportfolioevaluation;

import java.util.List;

public interface StockPortfolioEvaluationRepositoryCustom {
     public abstract void bulkInsert(List<StockPortfolioEvaluation> stockPortfolioEvaluationList);
}
