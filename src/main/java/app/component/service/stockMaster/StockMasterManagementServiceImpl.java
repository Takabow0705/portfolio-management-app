package app.component.service.stockMaster;

import app.commons.entities.products.StockMaster;
import app.component.repository.product.StockMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("stockMasterManagementService")
public class StockMasterManagementServiceImpl implements StockMasterManagementService {

    @Autowired
    private StockMasterRepository stockMasterRepository;

    @Override
    public List<StockMaster> findAll() {
        return this.stockMasterRepository.findAll();
    }
}
