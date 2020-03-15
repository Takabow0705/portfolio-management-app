package app.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum BondPricingAlgorithmsEnum {
    DISCOUNT_FACTOR("所与の現在割引率を用いて理論価格を計算します。","/calc/bond/df");

    @Getter
    private String algorithmDetail;
    @Getter
    private String apiPath;
}
