package project.calculator.domain.finance.bond.algo.pricing;

import io.grpc.util.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.calculator.data.BondPricingData;
import project.calculator.data.response.CalculationResult;
import project.calculator.domain.finance.bond.algo.CalculationStrategy;
import project.infra.rdb.discountfactor.DiscountFactorDataRepositoryMockImpl;
import project.infra.rdb.discountfactor.PaymentType;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

//ToDo Builderパターンへの書き換え。
class BondTheoreticalPricingStrategyTest {


    private CalculationStrategy<BondPricingData> algo;

    @BeforeEach
    void setUp() {
        this.algo = new BondTheoreticalPricingStrategy(new DiscountFactorDataRepositoryMockImpl());
    }

    /**
     * クーポンレートが5%未満のときは債券額面以下の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsUnder5PercentBondPriceIsUnderPar() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.03") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("94.553")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * クーポンレートが5%のときは債券額面と同一の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsEqualsTo5PercentBondPriceIsPar() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.05") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("100.000")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }
    /**
     * クーポンレートが5%より大きいときは債券額面以上の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsOver5PercentBondPriceIsOverPar() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.07") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("105.446")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * クーポンレートが5%未満のときは債券額面以下の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     * これはクーポン半期払いの場合。
     */
    @Test
    void whenCouponRateIsUnder5PercentAndSemiAnnualBondPriceIsUnderPar() {
       BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.03") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("94.491")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * クーポンレートが5%のときは債券額面と同一の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     * これはクーポン半期払いの場合。
     */
    @Test
    void whenCouponRateIsUnder5PercentAndSemiAnnualBondPriceIsPar() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.05") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("100.000")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * クーポンレートが5%以上のときは債券額面以上の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     * これはクーポン半期払いの場合。
     */
    @Test
    void whenCouponRateIsUnder5PercentAndSemiAnnualBondPriceIsOverPar() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.07") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("105.508")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * 割引債のケースで、割引現在価値として半期支払いのものを使う場合。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsZeroAndSemiAnnualBond() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.00") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("86.229")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * 割引債のケースで、割引現在価値として一年支払いのものを使う場合。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsZeroAndAnnualBondPrice() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.00") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("86.383")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }

    /**
     * 残存期間が一年未満の場合。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCuurentMaturityIsZeroAndAnnualBondPrice() {
        BondPricingData data = new BondPricingData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.00") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("0") //
        );

        CalculationResult actual = this.algo.execute(data);

        assertThat(new BigDecimal("100.00")
                , is(closeTo(actual.getResult(), new BigDecimal("0.001"))) //
        );
        assertThat(actual.getResultDetail().getStatus(), is(Status.OK));
    }
}