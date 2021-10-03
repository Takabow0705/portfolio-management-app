package project.infra.rdb.cashflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnrealizedCashflowRepository extends JpaRepository<UnrealizedCashflow, Long> {
}
