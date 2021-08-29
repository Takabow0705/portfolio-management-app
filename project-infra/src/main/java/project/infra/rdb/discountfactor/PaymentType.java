package project.infra.rdb.discountfactor;

import java.math.BigDecimal;

public enum PaymentType {
    Annual(new BigDecimal("1"), "ANNUAL"),
    SemiAnnual(new BigDecimal("0.5"), "SEMI_ANNUAL");

    private final BigDecimal step;
    private final String dbValue;

    private PaymentType(BigDecimal step, String dbValue) {
        this.step = step;
        this.dbValue = dbValue;
    }

    public BigDecimal getStep() {
        return this.step;
    }

    public String getDbValue() {
        return this.dbValue;
    }
}
