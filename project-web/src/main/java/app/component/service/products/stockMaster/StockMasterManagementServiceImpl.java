package app.component.service.products.stockMaster;

import app.commons.dto.SearchCondition;
import app.commons.entities.products.StockMaster;
import app.component.repository.product.StockMasterRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("stockMasterManagementService")
public class StockMasterManagementServiceImpl implements StockMasterManagementService {

    @Autowired
    private StockMasterRepository stockMasterRepository;

    private static final Long STOCK_CODE_MAX = 9999L;

    private static final Long STOCK_CODE_MIN = 0L;

    @Override
    public List<StockMaster> findAll() {
        return this.stockMasterRepository.findAll();
    }

    @Override
    public List<StockMaster> findByCondition(SearchCondition condition) {
        //検索条件の取り出し
        Long from = condition.getFromNumber() == null? STOCK_CODE_MIN : condition.getFromNumber();
        Long to = condition.getToNumber() == null? STOCK_CODE_MAX : condition.getToNumber();

        List<StockMaster> searchedStockList = this.stockMasterRepository.findAllBy(from, to);

        //部分一致検索をしないならここで終了
        if(condition.getPartialMatchText() == null){
            return searchedStockList;
        }

        return searchedStockList.stream()
                                .filter(s -> StringUtils.contains(s.getStockName(), condition.getPartialMatchText()))
                                .collect(Collectors.toList());
    }
}
