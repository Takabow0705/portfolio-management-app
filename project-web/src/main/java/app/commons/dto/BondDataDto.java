package app.commons.dto;

import app.commons.enums.BondPricingAlgorithmsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class BondDataDto {
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

    public BondDataDto(){}
}
