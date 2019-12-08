package app.component.service.bondMaster;

import app.commons.entities.BondMaster;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public interface BondMasterManagementService {

    abstract List<BondMaster> findAll();
}
