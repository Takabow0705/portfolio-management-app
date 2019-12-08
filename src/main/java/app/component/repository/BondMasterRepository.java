package app.component.repository;

import app.commons.entities.BondMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondMasterRepository extends JpaRepository<BondMaster,Long> {
}
