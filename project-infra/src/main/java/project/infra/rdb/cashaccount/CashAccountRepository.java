package project.infra.rdb.cashaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccount, Long> {
}
