package app.component.repository.product;

import app.commons.entities.products.CurrencyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyMasterRepository extends JpaRepository<CurrencyMaster,Long> {
}
