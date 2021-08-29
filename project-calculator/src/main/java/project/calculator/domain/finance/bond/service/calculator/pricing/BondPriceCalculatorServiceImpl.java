package project.calculator.domain.finance.bond.service.calculator.pricing;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import project.calculator.data.BondPricingData;
import project.calculator.data.response.CalculationResult;
import project.calculator.domain.finance.bond.algo.CalculationStrategy;

@Service
public class BondPriceCalculatorServiceImpl implements BondPriceCalculatorService {

    private final CalculationStrategy<BondPricingData> bondPricingDataCalculationStrategy;

    public BondPriceCalculatorServiceImpl(CalculationStrategy<BondPricingData> bondPricingDataCalculationStrategy){
        this.bondPricingDataCalculationStrategy = bondPricingDataCalculationStrategy;
    }

    /**
     *
     */
    @Cacheable("bondPriceCache")
    public CalculationResult calculateByDiscountFactor(BondPricingData data){
        CalculationResult result = this.bondPricingDataCalculationStrategy.execute(data);
        return result;
    }
}
