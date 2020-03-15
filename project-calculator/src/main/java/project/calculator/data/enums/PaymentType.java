package project.calculator.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.calculator.data.converter.BigDecimalConverter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum PaymentType {
    Annual(BigDecimalConverter.convertFrom("1"), "ANNUAL"),
    SemiAnnual(BigDecimalConverter.convertFrom("0.5"), "SEMI_ANNUAL");

    private final BigDecimal step;
    private final String dbValue;
}
