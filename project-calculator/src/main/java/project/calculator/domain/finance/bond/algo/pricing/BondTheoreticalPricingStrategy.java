package project.calculator.domain.finance.bond.algo.pricing;

import io.grpc.util.Status;
import io.grpc.util.StatusMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.calculator.data.BondPricingData;
import project.calculator.data.enums.PricingMethod;
import project.calculator.data.response.CalculationResult;
import project.calculator.domain.finance.bond.algo.CalculationStrategy;
import project.infra.rdb.discountfactor.DiscountFactorDataRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CalculationStrategyConfigクラスで管理するのでAutoConfiguration用アノテーションは不用
 */
public class BondTheoreticalPricingStrategy implements CalculationStrategy<BondPricingData> {

    private final DiscountFactorDataRepository discountFactorDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(BondTheoreticalPricingStrategy.class);

    public BondTheoreticalPricingStrategy(DiscountFactorDataRepository discountFactorDataRepository) {
        this.discountFactorDataRepository = discountFactorDataRepository;
    }

    @Override
    public CalculationResult execute(BondPricingData data) {
        List<BigDecimal> discountFactor = this.handleDiscountFactor(data).stream()
                .limit(data.getCurrentMaturity().divide(data.getPaymentType().getStep()).longValue() + 1)
                .collect(Collectors.toList());

        if(isDiscountFactorNotEnough(data, discountFactor)){
            logger.info("Discount Factor data is not sufficient.");
            StatusMsg msg = StatusMsg.newBuilder().setStatus(Status.NOT_COMPLETED).setDetail("Discount Factor data is not sufficient.").build();
            CalculationResult result = CalculationResult.create(BigDecimal.ZERO, PricingMethod.DISCOUNT_FACTOR, msg);
            return result;
        }

        BigDecimal NPVOfPrincipal = discountFactor.get(discountFactor.size() - 1).multiply(data.getUnit());

        if (data.getIsTermEndPayment()) {
            BigDecimal NPVOfCoupon = discountFactor.stream().skip(1)
                    .map(d -> d.multiply(data.getCouponRate()))
                    .map(d -> d.multiply(data.getPaymentType().getStep()))
                    .limit(data.getCurrentMaturity().divide(data.getPaymentType().getStep()).longValue())
                    .reduce(BigDecimal.ZERO, (p, q) -> p.add(q));

            BigDecimal theoreticalPrice = NPVOfPrincipal.add(NPVOfCoupon.multiply(data.getUnit()));
            StatusMsg msg = StatusMsg.newBuilder().setStatus(Status.OK).setDetail("Success").build();
            CalculationResult result = CalculationResult.create(theoreticalPrice, PricingMethod.DISCOUNT_FACTOR, msg);
            return result;
        }

        BigDecimal NPVOfCoupon = discountFactor.stream()
                .map(d -> d.multiply(data.getCouponRate()))
                .map(d -> d.multiply(data.getPaymentType().getStep()))
                .limit(data.getCurrentMaturity().longValue())
                .reduce(BigDecimal.ZERO, (p, q) -> p.add(q));

        BigDecimal theoreticalPrice = NPVOfPrincipal.add(NPVOfCoupon.multiply(data.getUnit()));
        StatusMsg msg = StatusMsg.newBuilder().setStatus(Status.OK).setDetail("Success").build();
        CalculationResult result = CalculationResult.create(theoreticalPrice, PricingMethod.DISCOUNT_FACTOR, msg);
        return result;
    }

    /**
     * <p>
     * 利払い周期によって使用する割引現在価値を切り替える。
     * </p>
     *
     * @param bondPricingData
     * @return 割引現在価値のMap
     */
    private List<BigDecimal> handleDiscountFactor(BondPricingData bondPricingData) {
        switch (bondPricingData.getPaymentType()) {
            case Annual:
                return this.discountFactorDataRepository.loadAnnualDiscountFactor();
            case SemiAnnual:
                return this.discountFactorDataRepository.loadSemiAnnualDiscountFactor();
            default:
                return Collections.emptyList();
        }
    }

    /**
     * <p>
     * 計算に必要な割引現在価値が存在するか確認
     * </p>
     *
     * @param data
     * @return
     */
    private Boolean isDiscountFactorNotEnough(BondPricingData data, List<BigDecimal> discountFactors){
        // 残存期間 * （半期 or 通期）で必要な割引現在価値の数を計算する。
        Integer termLength = data.getCurrentMaturity().intValue() * BigDecimal.ONE.divide(data.getPaymentType().getStep()).intValue();
        // 割引現在価値の数を求める。
        Integer discountFactorSize = discountFactors.size();
        return termLength > discountFactorSize;
    }
}
