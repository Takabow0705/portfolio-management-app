package app.component.service.stockMaster;

import app.commons.entities.products.BondMaster;
import app.commons.entities.products.StockMaster;

import java.util.List;

public interface StockMasterManagementService {

    abstract List<StockMaster> findAll();
}
