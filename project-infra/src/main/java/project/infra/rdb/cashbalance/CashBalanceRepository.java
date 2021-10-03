package project.infra.rdb.cashbalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashBalanceRepository extends JpaRepository<CashBalance, Long> {
}
