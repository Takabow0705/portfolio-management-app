package app.domain.calculation.bond;


public enum BondPricingAlgorithmsEnum {
    DISCOUNT_FACTOR("所与の現在割引率を用いて理論価格を計算します。","/calc/bond/df");

    private String algorithmDetail;
    private String apiPath;

    private BondPricingAlgorithmsEnum(String algorithmDetail, String apiPath) {
        this.algorithmDetail = algorithmDetail;
        this.apiPath = apiPath;
    }

    public String getAlgorithmDetail() {
        return this.algorithmDetail;
    }

    public String getApiPath() {
        return this.apiPath;
    }
}
