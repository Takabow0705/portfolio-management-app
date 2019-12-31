package app.component.service.products.currencyMaster;

import app.commons.entities.products.CurrencyMaster;

import java.util.List;

public interface CurrencyMasterManagementService {

    abstract List<CurrencyMaster> findAll();
}
