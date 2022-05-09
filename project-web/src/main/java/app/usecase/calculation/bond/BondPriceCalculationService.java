package app.usecase.calculation.bond;

import app.domain.calculation.bond.BondDataDto;

public interface BondPriceCalculationService {
    public abstract BondDataDto calculateBondPriceByDiscountFactor(BondDataDto request);
}
