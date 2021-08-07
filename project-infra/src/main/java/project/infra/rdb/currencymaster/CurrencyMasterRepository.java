package project.infra.rdb.currencymaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CurrencyMasterRepository extends JpaRepository<CurrencyMaster,Long> {

    @Query("select s from CurrencyMaster s where s.currencyCode in :currencyCodes")
    public Set<CurrencyMaster> findByCurrencyCode(@Param("currencyCodes") Set<String> currencyCodes);
}
