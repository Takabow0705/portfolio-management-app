package project.calculator.domain.finance.bond.service.calculator.pricing;

import project.calculator.data.BondPricingData;
import project.calculator.data.response.CalculationResult;

public interface BondPriceCalculatorService {

    abstract CalculationResult calculateByDisCountFactor(BondPricingData data);
}
