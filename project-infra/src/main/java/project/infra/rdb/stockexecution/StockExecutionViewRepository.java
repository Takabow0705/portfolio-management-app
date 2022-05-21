package project.infra.rdb.stockexecution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import project.infra.rdb.stockexecution.entity.StockExecutionView;

@Repository
public interface StockExecutionViewRepository extends JpaRepository<StockExecutionView, Long>, JpaSpecificationExecutor<StockExecutionView> {}
