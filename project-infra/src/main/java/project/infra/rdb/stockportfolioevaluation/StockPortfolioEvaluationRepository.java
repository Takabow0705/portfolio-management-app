package project.infra.rdb.stockportfolioevaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockPortfolioEvaluationRepository extends JpaRepository<StockPortfolioEvaluation, Long> {

    public List<StockPortfolioEvaluation> findAllByStockPortfolioIdEqualsAndBaseDateBeforeAndBaseDateAfter(long stockPortfolioId, LocalDate endDate, LocalDate startDate);
    public List<StockPortfolioEvaluation> findAllByStockPortfolioIdEqualsAndLockOutTrueAndDeletedFalse(long stockPortfolioId);
}
