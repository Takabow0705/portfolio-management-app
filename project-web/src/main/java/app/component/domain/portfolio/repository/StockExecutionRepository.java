package app.component.domain.portfolio.repository;

import app.commons.entities.portfolio.execution.StockExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExecutionRepository extends JpaRepository<StockExecution, Long> , JpaSpecificationExecutor<StockExecution> {
}
