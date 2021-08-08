package project.infra.rdb.stockportfolioevaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPortfolioEvaluationRepository extends JpaRepository<StockPortfolioEvaluation, Long> {
}
