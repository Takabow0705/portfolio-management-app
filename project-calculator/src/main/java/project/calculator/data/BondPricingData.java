package project.calculator.data;

import project.calculator.data.enums.PaymentType;

import java.math.BigDecimal;

//ToDo ユニットテストをstatic factoryに対応させる。
public final class BondPricingData {
    private final String bondCode;
    private final BigDecimal currentPrice;
    private final BigDecimal bookPrice;
    private final BigDecimal unit;
    //年率計算で入れる
    private final BigDecimal couponRate;
    private final PaymentType paymentType;
    private final Boolean isTermEndPayment;
    // 一年or半年単位で端数を切り捨てする。
    private final BigDecimal currentMaturity;

    public BondPricingData(String bondCode, BigDecimal currentPrice, BigDecimal bookPrice, BigDecimal unit, BigDecimal couponRate, PaymentType paymentType, Boolean isTermEndPayment, BigDecimal currentMaturity) {
        this.bondCode = bondCode;
        this.currentPrice = currentPrice;
        this.bookPrice = bookPrice;
        this.unit = unit;
        this.couponRate = couponRate;
        this.paymentType = paymentType;
        this.isTermEndPayment = isTermEndPayment;
        this.currentMaturity = currentMaturity;
    }

    public static BondPricingDataBuilder builder() {
        return new BondPricingDataBuilder();
    }

    public String getBondCode() {
        return this.bondCode;
    }

    public BigDecimal getCurrentPrice() {
        return this.currentPrice;
    }

    public BigDecimal getBookPrice() {
        return this.bookPrice;
    }

    public BigDecimal getUnit() {
        return this.unit;
    }

    public BigDecimal getCouponRate() {
        return this.couponRate;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public Boolean getIsTermEndPayment() {
        return this.isTermEndPayment;
    }

    public BigDecimal getCurrentMaturity() {
        return this.currentMaturity;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BondPricingData)) return false;
        final BondPricingData other = (BondPricingData) o;
        final Object this$bondCode = this.getBondCode();
        final Object other$bondCode = other.getBondCode();
        if (this$bondCode == null ? other$bondCode != null : !this$bondCode.equals(other$bondCode)) return false;
        final Object this$currentPrice = this.getCurrentPrice();
        final Object other$currentPrice = other.getCurrentPrice();
        if (this$currentPrice == null ? other$currentPrice != null : !this$currentPrice.equals(other$currentPrice))
            return false;
        final Object this$bookPrice = this.getBookPrice();
        final Object other$bookPrice = other.getBookPrice();
        if (this$bookPrice == null ? other$bookPrice != null : !this$bookPrice.equals(other$bookPrice)) return false;
        final Object this$unit = this.getUnit();
        final Object other$unit = other.getUnit();
        if (this$unit == null ? other$unit != null : !this$unit.equals(other$unit)) return false;
        final Object this$couponRate = this.getCouponRate();
        final Object other$couponRate = other.getCouponRate();
        if (this$couponRate == null ? other$couponRate != null : !this$couponRate.equals(other$couponRate))
            return false;
        final Object this$paymentType = this.getPaymentType();
        final Object other$paymentType = other.getPaymentType();
        if (this$paymentType == null ? other$paymentType != null : !this$paymentType.equals(other$paymentType))
            return false;
        final Object this$isTermEndPayment = this.getIsTermEndPayment();
        final Object other$isTermEndPayment = other.getIsTermEndPayment();
        if (this$isTermEndPayment == null ? other$isTermEndPayment != null : !this$isTermEndPayment.equals(other$isTermEndPayment))
            return false;
        final Object this$currentMaturity = this.getCurrentMaturity();
        final Object other$currentMaturity = other.getCurrentMaturity();
        if (this$currentMaturity == null ? other$currentMaturity != null : !this$currentMaturity.equals(other$currentMaturity))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bondCode = this.getBondCode();
        result = result * PRIME + ($bondCode == null ? 43 : $bondCode.hashCode());
        final Object $currentPrice = this.getCurrentPrice();
        result = result * PRIME + ($currentPrice == null ? 43 : $currentPrice.hashCode());
        final Object $bookPrice = this.getBookPrice();
        result = result * PRIME + ($bookPrice == null ? 43 : $bookPrice.hashCode());
        final Object $unit = this.getUnit();
        result = result * PRIME + ($unit == null ? 43 : $unit.hashCode());
        final Object $couponRate = this.getCouponRate();
        result = result * PRIME + ($couponRate == null ? 43 : $couponRate.hashCode());
        final Object $paymentType = this.getPaymentType();
        result = result * PRIME + ($paymentType == null ? 43 : $paymentType.hashCode());
        final Object $isTermEndPayment = this.getIsTermEndPayment();
        result = result * PRIME + ($isTermEndPayment == null ? 43 : $isTermEndPayment.hashCode());
        final Object $currentMaturity = this.getCurrentMaturity();
        result = result * PRIME + ($currentMaturity == null ? 43 : $currentMaturity.hashCode());
        return result;
    }

    public String toString() {
        return "BondPricingData(bondCode=" + this.getBondCode() + ", currentPrice=" + this.getCurrentPrice() + ", bookPrice=" + this.getBookPrice() + ", unit=" + this.getUnit() + ", couponRate=" + this.getCouponRate() + ", paymentType=" + this.getPaymentType() + ", isTermEndPayment=" + this.getIsTermEndPayment() + ", currentMaturity=" + this.getCurrentMaturity() + ")";
    }

    public static class BondPricingDataBuilder {
        private String bondCode;
        private BigDecimal currentPrice;
        private BigDecimal bookPrice;
        private BigDecimal unit;
        private BigDecimal couponRate;
        private PaymentType paymentType;
        private Boolean isTermEndPayment;
        private BigDecimal currentMaturity;

        BondPricingDataBuilder() {
        }

        public BondPricingDataBuilder bondCode(String bondCode) {
            this.bondCode = bondCode;
            return this;
        }

        public BondPricingDataBuilder currentPrice(BigDecimal currentPrice) {
            this.currentPrice = currentPrice;
            return this;
        }

        public BondPricingDataBuilder bookPrice(BigDecimal bookPrice) {
            this.bookPrice = bookPrice;
            return this;
        }

        public BondPricingDataBuilder unit(BigDecimal unit) {
            this.unit = unit;
            return this;
        }

        public BondPricingDataBuilder couponRate(BigDecimal couponRate) {
            this.couponRate = couponRate;
            return this;
        }

        public BondPricingDataBuilder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public BondPricingDataBuilder isTermEndPayment(Boolean isTermEndPayment) {
            this.isTermEndPayment = isTermEndPayment;
            return this;
        }

        public BondPricingDataBuilder currentMaturity(BigDecimal currentMaturity) {
            this.currentMaturity = currentMaturity;
            return this;
        }

        public BondPricingData build() {
            return new BondPricingData(bondCode, currentPrice, bookPrice, unit, couponRate, paymentType, isTermEndPayment, currentMaturity);
        }

        public String toString() {
            return "BondPricingData.BondPricingDataBuilder(bondCode=" + this.bondCode + ", currentPrice=" + this.currentPrice + ", bookPrice=" + this.bookPrice + ", unit=" + this.unit + ", couponRate=" + this.couponRate + ", paymentType=" + this.paymentType + ", isTermEndPayment=" + this.isTermEndPayment + ", currentMaturity=" + this.currentMaturity + ")";
        }
    }
}
