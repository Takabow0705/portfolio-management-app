package project.calculator.domain.batch.porfolio;

import project.infra.rdb.stockexecution.BuySellType;
import project.infra.rdb.stockexecution.entity.StockExecution;
import project.infra.rdb.stockexecution.entity.StockExecutionBase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DailyPosition {
    
    private final LocalDate baseDate;

    private final String stockCode;

    private final long stockPortfolioId;

    private final BigDecimal shortAmount;

    private final BigDecimal longAmount;

    private final BigDecimal shortValue;

    private final BigDecimal longValue;

    private final String currencyCode;

    public DailyPosition(LocalDate baseDate, String stockCode, long stockPortfolioId, BigDecimal shortAmount, BigDecimal longAmount, BigDecimal shortValue, BigDecimal longValue, String currencyCode) {
        this.baseDate = baseDate;
        this.stockCode = stockCode;
        this.stockPortfolioId = stockPortfolioId;
        this.shortAmount = shortAmount;
        this.longAmount = longAmount;
        this.shortValue = shortValue;
        this.longValue = longValue;
        this.currencyCode = currencyCode;
    }

    /**
     * 本日の買いポジション残高を計算する。
     * @return
     */
    public BigDecimal calculateLongValue(){
        return this.longAmount.multiply(this.longValue);
    }

    /**
     * 本日のポジション残高を計算する。
     * @return
     */
    public BigDecimal calculateTotalAmount(){
        return this.shortAmount.add(this.longAmount);
    }

    /**
     * 約定データから1基準日の1銘柄のポジションを表現するDailyPositionクラスを生成する。
     * 引数の約定は予め1銘柄のものだけになっていることを期待する。
     * @param baseDate
     * @param executions
     * @return
     */
    public static DailyPosition aggregateDailyExecution(LocalDate baseDate, List<StockExecution> executions){
        Set<String> stockCodes = executions.stream().map(StockExecution::getStockCode).collect(Collectors.toSet());
        if(stockCodes == null || stockCodes.size() != 1){
            throw new IllegalArgumentException(String.format("Execution contain more than 1 Product. codes are %s", stockCodes.toString()));
        }

        String stockCode = executions.stream().map(StockExecutionBase::getStockCode).findFirst().orElseThrow(IllegalArgumentException::new);
        String currencyCode = executions.stream().map(StockExecutionBase::getCurrencyCode).findFirst().orElseThrow(IllegalArgumentException::new);
        long stockPortfolioId = executions.stream().map(StockExecutionBase::getStockPortfolioId).findFirst().orElseThrow(IllegalArgumentException::new);

        List<StockExecution> longPosition = executions.stream().filter(s -> s.getBuySellType() == BuySellType.BUY)
                .filter(s -> s.getExecutionDate().equals(baseDate)).collect(Collectors.toList());

        List<StockExecution> shortPosition = executions.stream().filter(s -> s.getBuySellType() == BuySellType.SELL)
                .filter(s -> s.getExecutionDate().equals(baseDate)).collect(Collectors.toList());

        // 買いポジションの加重平均簿価を算出する。
        BigDecimal totalLongAmount = calculateTotalAmount(longPosition);
        BigDecimal totalLongValue = calculateTotalValue(longPosition);
        BigDecimal longAverageValue = calculateSafeDivide(totalLongValue, totalLongAmount);

        // 売りポジションの加重平均簿価を算出する。
        BigDecimal totalShortAmount = calculateTotalAmount(shortPosition).negate();
        BigDecimal totalShortValue = calculateTotalValue(shortPosition);
        BigDecimal shortAverageValue = calculateSafeDivide(totalShortValue, totalShortAmount).abs();

        return new DailyPosition(baseDate, stockCode, stockPortfolioId, totalShortAmount, totalLongAmount, shortAverageValue, longAverageValue, currencyCode);
    }

    private static BigDecimal calculateTotalAmount(List<StockExecution> executions){
        return executions.stream().map(StockExecutionBase::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calculateTotalValue(List<StockExecution> executions){
        return executions.stream().map(s -> s.getBookValue().multiply(s.getAmount())).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calculateSafeDivide(BigDecimal numerator, BigDecimal denominator){
        if (denominator == null || denominator.equals(BigDecimal.ZERO)){
            return BigDecimal.ZERO;
        }
        return numerator.divide(denominator, 10, RoundingMode.DOWN);
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public String getStockCode() {
        return stockCode;
    }

    public long getStockPortfolioId() {
        return stockPortfolioId;
    }

    public BigDecimal getShortAmount() {
        return shortAmount;
    }

    public BigDecimal getLongAmount() {
        return longAmount;
    }

    public BigDecimal getShortValue() {
        return shortValue;
    }

    public BigDecimal getLongValue() {
        return longValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyPosition that = (DailyPosition) o;
        return baseDate.equals(that.baseDate) && stockCode.equals(that.stockCode) && currencyCode.equals(that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseDate, stockCode, currencyCode);
    }
}
