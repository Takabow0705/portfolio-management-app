package project.infra.rdb.stockexecution;

public enum BuySellType {
    BUY("BUY")
    ,SELL("SELL")
    ,OTHER("OTHER");

    BuySellType(String buySellType){
        this.buySellType = buySellType;
    }
    private final String buySellType;

    public static BuySellType convertFrom(String buySellType){
        switch (buySellType){
            case "BUY" :
                return BuySellType.BUY;
            case "SELL" :
                return BuySellType.SELL;
            case "OTHER":
                return BuySellType.OTHER;
            default:
                throw new IllegalArgumentException(String.format("Unexpected Argument. buySellType = %d", buySellType));
        }
    }
    public String getType() {
        return buySellType;
    }
}
