package app.component.service.products.stockMaster;

import app.commons.dto.SearchCondition;
import app.commons.entities.products.StockMaster;

import java.util.List;

public interface StockMasterManagementService {

    abstract List<StockMaster> findAll();

    abstract List<StockMaster> findByCondition(SearchCondition condition);
}
