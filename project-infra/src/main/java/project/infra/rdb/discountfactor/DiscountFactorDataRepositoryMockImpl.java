package project.infra.rdb.discountfactor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 5%の金利のときの現在価値割引を返す。
 * 本番では債券価格から逆算された現在価値のマスターデータを用いる。
 */
@Repository
@Qualifier("mock")
public class DiscountFactorDataRepositoryMockImpl implements DiscountFactorDataRepository {

    private final Map<Double, BigDecimal> annualDiscountFactor;
    private final Map<Double, BigDecimal> semiAnnualDiscountFactor;

    public DiscountFactorDataRepositoryMockImpl(){
        Map<Double, BigDecimal> annual = new LinkedHashMap<>();
        annual.put(Double.valueOf("0"), new BigDecimal("1"));
        annual.put(Double.valueOf("1"), new BigDecimal("0.9523809524"));
        annual.put(Double.valueOf("2"), new BigDecimal("0.9070294785"));
        annual.put(Double.valueOf("3"), new BigDecimal("0.8638375985"));
        annual.put(Double.valueOf("4"), new BigDecimal("0.8227024748"));
        annual.put(Double.valueOf("5"), new BigDecimal("0.7835261665"));
        annual.put(Double.valueOf("6"), new BigDecimal("0.7462153966"));
        this.annualDiscountFactor = annual;

        Map<Double, BigDecimal> semiAnnual = new LinkedHashMap<>();
        semiAnnual.put(Double.valueOf("0"), new BigDecimal("1"));
        semiAnnual.put(Double.valueOf("0.5"), new BigDecimal("0.9756097561"));
        semiAnnual.put(Double.valueOf("1"), new BigDecimal("0.9518143962"));
        semiAnnual.put(Double.valueOf("1.5"), new BigDecimal("0.9285994109"));
        semiAnnual.put(Double.valueOf("2"), new BigDecimal("0.9059506448"));
        semiAnnual.put(Double.valueOf("2.5"), new BigDecimal("0.8838542876"));
        semiAnnual.put(Double.valueOf("3"), new BigDecimal("0.862296866"));
        semiAnnual.put(Double.valueOf("3.5"), new BigDecimal("0.8412652351"));
        semiAnnual.put(Double.valueOf("4"), new BigDecimal("0.8207465708"));
        semiAnnual.put(Double.valueOf("4.5"), new BigDecimal("0.8007283618"));
        semiAnnual.put(Double.valueOf("5"), new BigDecimal("0.7811984017"));
        semiAnnual.put(Double.valueOf("5.5"), new BigDecimal("0.7621447822"));
        semiAnnual.put(Double.valueOf("6"), new BigDecimal("0.743555885"));
        this.semiAnnualDiscountFactor = semiAnnual;
    }

    @Override
    public List<BigDecimal> loadAnnualDiscountFactor() {
        return this.annualDiscountFactor.values().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<BigDecimal> loadSemiAnnualDiscountFactor() {
        return this.semiAnnualDiscountFactor.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
