package app.component.domain.portfolio.repository;

import app.commons.entities.portfolio.StockExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockExecutionRepository extends JpaRepository<StockExecution, Long> {
}
