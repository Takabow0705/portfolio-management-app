package app.component.repository.product;

import app.commons.entities.products.BondMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondMasterRepository extends JpaRepository<BondMaster,Long> {
}
