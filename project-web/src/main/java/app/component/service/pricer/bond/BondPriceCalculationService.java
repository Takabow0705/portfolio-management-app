package app.component.service.pricer.bond;

import app.commons.dto.BondDataDto;

public interface BondPriceCalculationService {
    public abstract BondDataDto calculateBondPriceByDiscountFactor(BondDataDto request);
}
