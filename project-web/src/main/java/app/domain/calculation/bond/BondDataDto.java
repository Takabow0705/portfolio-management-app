package app.domain.calculation.bond;

import java.util.UUID;

public class BondDataDto {
    private UUID uuid;
    private String bondCode;
    private String currentPrice;
    private String bookPrice;
    private String unit;
    //年率計算で入れる
    private String couponRate;
    private String paymentType;
    private Boolean isTermEndPayment;
    // 一年or半年単位で端数を切り捨てする。
    private String currentMaturity;
    private String theoreticalPrice;
    private BondPricingAlgorithmsEnum algorithms;
    private String errorDetails;

    public BondDataDto(){
        this.uuid = UUID.randomUUID();
    }

    private BondDataDto(String bondCode, String currentPrice, String bookPrice, String unit, String couponRate, String paymentType, Boolean isTermEndPayment, String currentMaturity, String theoreticalPrice, BondPricingAlgorithmsEnum algorithms) {
        this.uuid = UUID.randomUUID();
        this.bondCode = bondCode;
        this.currentPrice = currentPrice;
        this.bookPrice = bookPrice;
        this.unit = unit;
        this.couponRate = couponRate;
        this.paymentType = paymentType;
        this.isTermEndPayment = isTermEndPayment;
        this.currentMaturity = currentMaturity;
        this.theoreticalPrice = theoreticalPrice;
        this.algorithms = algorithms;
    }

    public static BondDataDto of(String bondCode, String currentPrice, String bookPrice, String unit, String couponRate, String paymentType, Boolean isTermEndPayment, String currentMaturity, String theoreticalPrice, BondPricingAlgorithmsEnum algorithms) {
        return new BondDataDto(bondCode, currentPrice, bookPrice, unit, couponRate, paymentType, isTermEndPayment, currentMaturity, theoreticalPrice, algorithms);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getBondCode() {
        return this.bondCode;
    }

    public String getCurrentPrice() {
        return this.currentPrice;
    }

    public String getBookPrice() {
        return this.bookPrice;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getCouponRate() {
        return this.couponRate;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public Boolean getIsTermEndPayment() {
        return this.isTermEndPayment;
    }

    public String getCurrentMaturity() {
        return this.currentMaturity;
    }

    public String getTheoreticalPrice() {
        return this.theoreticalPrice;
    }

    public BondPricingAlgorithmsEnum getAlgorithms() {
        return this.algorithms;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCouponRate(String couponRate) {
        this.couponRate = couponRate;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setIsTermEndPayment(Boolean isTermEndPayment) {
        this.isTermEndPayment = isTermEndPayment;
    }

    public void setCurrentMaturity(String currentMaturity) {
        this.currentMaturity = currentMaturity;
    }

    public void setTheoreticalPrice(String theoreticalPrice) {
        this.theoreticalPrice = theoreticalPrice;
    }

    public void setAlgorithms(BondPricingAlgorithmsEnum algorithms) {
        this.algorithms = algorithms;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BondDataDto)) return false;
        final BondDataDto other = (BondDataDto) o;
        if (!other.canEqual((Object) this)) return false;
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
        final Object this$theoreticalPrice = this.getTheoreticalPrice();
        final Object other$theoreticalPrice = other.getTheoreticalPrice();
        if (this$theoreticalPrice == null ? other$theoreticalPrice != null : !this$theoreticalPrice.equals(other$theoreticalPrice))
            return false;
        final Object this$algorithms = this.getAlgorithms();
        final Object other$algorithms = other.getAlgorithms();
        if (this$algorithms == null ? other$algorithms != null : !this$algorithms.equals(other$algorithms))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BondDataDto;
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
        final Object $theoreticalPrice = this.getTheoreticalPrice();
        result = result * PRIME + ($theoreticalPrice == null ? 43 : $theoreticalPrice.hashCode());
        final Object $algorithms = this.getAlgorithms();
        result = result * PRIME + ($algorithms == null ? 43 : $algorithms.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BondDataDto{" +
                "uuid=" + uuid +
                ", bondCode='" + bondCode + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", bookPrice='" + bookPrice + '\'' +
                ", unit='" + unit + '\'' +
                ", couponRate='" + couponRate + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", isTermEndPayment=" + isTermEndPayment +
                ", currentMaturity='" + currentMaturity + '\'' +
                ", theoreticalPrice='" + theoreticalPrice + '\'' +
                ", algorithms=" + algorithms +
                ", errorDetails='" + errorDetails + '\'' +
                '}';
    }
}
