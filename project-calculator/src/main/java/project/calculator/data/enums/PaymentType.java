package project.calculator.data.enums;

import project.calculator.data.converter.BigDecimalConverter;

import java.math.BigDecimal;

public enum PaymentType {
    Annual(BigDecimalConverter.convertFrom("1"), "ANNUAL"),
    SemiAnnual(BigDecimalConverter.convertFrom("0.5"), "SEMI_ANNUAL");

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
