package project.infra.rdb.stockexecution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.infra.rdb.stockexecution.entity.StockExecution;

import javax.annotation.PostConstruct;

@Repository
public interface StockExecutionRepository extends JpaRepository<StockExecution, Long> , JpaSpecificationExecutor<StockExecution> {

}
