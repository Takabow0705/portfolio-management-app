package project.calculator.domain.repository.master.discountFactor;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountFactorDataRepository {

    abstract List<BigDecimal>  loadAnnualDiscountFactor();

    abstract List<BigDecimal> loadSemiAnnualDiscountFactor();
}
