package app.commons.entities.products;


import app.commons.entities.products.pk.BondMasterPK;
import app.commons.enums.finance.PaymentType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bond_master")
@IdClass(BondMasterPK.class)
public class BondMaster implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BOND_CODE")
    private String bondCode;
    @Id
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "BOND_NAME")
    private String bondName;
    @Column(name = "ISSUE_DATE")
    private Date issueDate;
    @Column(name = "MATURITY")
    private Date maturity;
    @Column(name = "COUPON_RATE")
    private BigDecimal couponRate;
    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "UNIT")
    private BigDecimal unit;
    @Column(name = "FACE_VALUE")
    private BigDecimal faceValue;
    @Column(name = "ISSUER_SECTOR_CODE")
    private String issuerSectorCode;
    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

    public BigDecimal getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(BigDecimal couponRate) {
        this.couponRate = couponRate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public String getIssuerSectorCode() {
        return issuerSectorCode;
    }

    public void setIssuerSectorCode(String issuerSectorCode) {
        this.issuerSectorCode = issuerSectorCode;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "BondMaster{" +
                "bondCode='" + bondCode + '\'' +
                ", version=" + version +
                ", bondName='" + bondName + '\'' +
                ", issueDate=" + issueDate +
                ", maturity=" + maturity +
                ", paymentType=" + paymentType +
                ", unit=" + unit +
                ", faceValue=" + faceValue +
                ", issuerSectorCode=" + issuerSectorCode +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BondMaster that = (BondMaster) o;
        return bondCode.equals(that.bondCode) &&
                version.equals(that.version) &&
                Objects.equals(bondName, that.bondName) &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(maturity, that.maturity) &&
                Objects.equals(couponRate, that.couponRate) &&
                paymentType == that.paymentType &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(faceValue, that.faceValue) &&
                Objects.equals(issuerSectorCode, that.issuerSectorCode) &&
                Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bondCode, version, bondName, issueDate, maturity, couponRate, paymentType, unit, faceValue, issuerSectorCode, isDeleted);
    }
}