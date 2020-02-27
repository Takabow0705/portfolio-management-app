package project.calculator.domain.finance.bond.algo.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.calculator.data.BondData;
import project.calculator.data.enums.PaymentType;
import project.calculator.domain.finance.bond.algo.CalculationStrategy;
import project.calculator.domain.repository.master.discountFactor.DiscountFactorDataRepositoryMockImpl;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

class BondTheoreticalPricingStrategyTest {


    private CalculationStrategy<BondData> algo;

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
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.03") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("94.553")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * クーポンレートが5%のときは債券額面と同一の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsEqualsTo5PercentBondPriceIsPar() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.05") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("100.000")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }
    /**
     * クーポンレートが5%より大きいときは債券額面以上の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsOver5PercentBondPriceIsOverPar() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.07") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("105.446")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * クーポンレートが5%未満のときは債券額面以下の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     * これはクーポン半期払いの場合。
     */
    @Test
    void whenCouponRateIsUnder5PercentAndSemiAnnualBondPriceIsUnderPar() {
       BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.03") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("94.491")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * クーポンレートが5%のときは債券額面と同一の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     * これはクーポン半期払いの場合。
     */
    @Test
    void whenCouponRateIsUnder5PercentAndSemiAnnualBondPriceIsPar() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.05") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("100.000")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * クーポンレートが5%以上のときは債券額面以上の理論価格となる。
     * 期待値はGoogleSpreadSheetにて計算済み。
     * これはクーポン半期払いの場合。
     */
    @Test
    void whenCouponRateIsUnder5PercentAndSemiAnnualBondPriceIsOverPar() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.07") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("105.508")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * 割引債のケースで、割引現在価値として半期支払いのものを使う場合。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsZeroAndSemiAnnualBond() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.00") //
                , PaymentType.SemiAnnual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("86.229")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * 割引債のケースで、割引現在価値として一年支払いのものを使う場合。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCouponRateIsZeroAndAnnualBondPrice() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.00") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("3") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("86.383")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }

    /**
     * 残存期間が一年未満の場合。
     * 期待値はGoogleSpreadSheetにて計算済み。
     */
    @Test
    void whenCuurentMaturityIsZeroAndAnnualBondPrice() {
        BondData data = new BondData("1000"
                , new BigDecimal("98") //
                , new BigDecimal("99") //
                , new BigDecimal("100") //
                , new BigDecimal("0.00") //
                , PaymentType.Annual //
                , Boolean.TRUE //
                , new BigDecimal("0") //
        );

        BigDecimal actual = this.algo.execute(data);

        assertThat(new BigDecimal("100.00")
                , is(closeTo(actual, new BigDecimal("0.001"))) //
        );
    }
}