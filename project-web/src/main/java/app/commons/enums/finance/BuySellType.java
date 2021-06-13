package app.commons.enums.finance;

public enum BuySellType {
    BUY(1)
    ,SELL(3)
    ,OTHER(9);

    private BuySellType(int buySellType){
        this.buySellType = buySellType;
    }
    private final int buySellType;

    public static BuySellType convertFrom(int buySellType){
        switch (buySellType){
            case 1 :
                return BuySellType.BUY;
            case 3 :
                return BuySellType.SELL;
            case 9:
                return BuySellType.OTHER;
            default:
                throw new IllegalArgumentException(String.format("Unexpected Argument. buySellType = %d", buySellType));
        }
    }
}
