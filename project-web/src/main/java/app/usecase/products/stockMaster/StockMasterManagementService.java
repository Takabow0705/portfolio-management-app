package app.usecase.products.stockMaster;

import app.domain.product.stockmaster.SearchCondition;
import project.infra.rdb.stockmaster.StockMaster;

import java.util.List;

public interface StockMasterManagementService {

    abstract List<StockMaster> findAll();

    abstract List<StockMaster> findByCondition(SearchCondition condition);
}
