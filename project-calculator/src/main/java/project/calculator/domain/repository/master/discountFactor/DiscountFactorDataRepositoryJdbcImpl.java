package project.calculator.domain.repository.master.discountFactor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Qualifier("prod")
public class DiscountFactorDataRepositoryJdbcImpl implements DiscountFactorDataRepository {


    @Override
    public List<BigDecimal> loadAnnualDiscountFactor() {
        return null;
    }

    @Override
    public List<BigDecimal> loadSemiAnnualDiscountFactor() {
        return null;
    }
}
