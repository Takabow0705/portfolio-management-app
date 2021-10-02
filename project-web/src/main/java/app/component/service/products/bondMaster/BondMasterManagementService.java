package app.component.service.products.bondMaster;


import project.infra.rdb.bondmaster.BondMaster;

import java.util.List;

public interface BondMasterManagementService {

    abstract List<BondMaster> findAll();
}
