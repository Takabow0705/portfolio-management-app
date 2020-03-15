package project.calculator.domain.finance.bond.service.calculator.pricing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.calculator.data.BondPricingData;
import project.calculator.data.response.CalculationResult;
import project.calculator.domain.finance.bond.algo.CalculationStrategy;

@Service
public class BondPriceCalculatorServiceImpl implements BondPriceCalculatorService {

    private final CalculationStrategy<BondPricingData> pricingAlgoByDF;

    public BondPriceCalculatorServiceImpl(
            @Qualifier("useDF") final CalculationStrategy<BondPricingData> algo
    ){
        this.pricingAlgoByDF = algo;
    }

    /**
     *
     */
    public CalculationResult calculateByDisCountFactor(BondPricingData data){
        CalculationResult result = this.pricingAlgoByDF.execute(data);
        return result;
    }

}
