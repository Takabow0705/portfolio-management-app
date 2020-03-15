package project.calculator.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import project.calculator.data.enums.PaymentType;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor //ToDo ユニットテストをstatic factoryに対応させる。
public class BondPricingData {
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
}
