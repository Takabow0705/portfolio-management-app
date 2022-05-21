package app.domain.execution;

import project.infra.rdb.stockexecution.BuySellType;
import project.infra.rdb.stockexecution.entity.StockExecutionView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class StockExecutionInquiryDto implements Comparable<StockExecutionInquiryDto> {
    private long stockExecutionId;
    private String portfolioName;
    private String stockName;
    private String currencyCode;
    private BigDecimal bookValue;
    private LocalDate executionDate;
    private LocalDate valueDate;
    private BuySellType buySellType;
    private BigDecimal amount;
    private boolean isExecutionDeleted;

    public StockExecutionInquiryDto(long stockExecutionId, String portfolioName, String stockName, String currencyCode, BigDecimal bookValue, LocalDate executionDate, LocalDate valueDate, BuySellType buySellType, BigDecimal amount, boolean isExecutionDeleted) {
        this.stockExecutionId = stockExecutionId;
        this.portfolioName = portfolioName;
        this.stockName = stockName;
        this.currencyCode = currencyCode;
        this.bookValue = bookValue;
        this.executionDate = executionDate;
        this.valueDate = valueDate;
        this.buySellType = buySellType;
        this.amount = amount;
        this.isExecutionDeleted = isExecutionDeleted;
    }

    public static StockExecutionInquiryDto map(StockExecutionView view){
        return new StockExecutionInquiryDto(
                view.getStockExecutionId(),
                view.getPortfolioName(),
                view.getStockName(),
                view.getCurrencyCode(),
                view.getBookValue(),
                view.getExecutionDate(),
                view.getValueDate(),
                view.getBuySellType(),
                view.getAmount(),
                view.isExecutionDeleted()
        );
    }

    public long getStockExecutionId() {
        return stockExecutionId;
    }

    public void setStockExecutionId(long stockExecutionId) {
        this.stockExecutionId = stockExecutionId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBookValue() {
        return bookValue;
    }

    public void setBookValue(BigDecimal bookValue) {
        this.bookValue = bookValue;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public BuySellType getBuySellType() {
        return buySellType;
    }

    public void setBuySellType(BuySellType buySellType) {
        this.buySellType = buySellType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isExecutionDeleted() {
        return isExecutionDeleted;
    }

    public void setExecutionDeleted(boolean executionDeleted) {
        isExecutionDeleted = executionDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockExecutionInquiryDto that = (StockExecutionInquiryDto) o;
        return stockExecutionId == that.stockExecutionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockExecutionId);
    }

    @Override
    public String toString() {
        return "StockExecutionInquiryDto{" +
                "stockExecutionId=" + stockExecutionId +
                ", portfolioName='" + portfolioName + '\'' +
                ", stockName='" + stockName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", bookValue=" + bookValue +
                ", executionDate=" + executionDate +
                ", valueDate=" + valueDate +
                ", buySellType=" + buySellType +
                ", amount=" + amount +
                ", isExecutionDeleted=" + isExecutionDeleted +
                '}';
    }

    @Override
    public int compareTo(StockExecutionInquiryDto o) {
        return o.getExecutionDate().compareTo(this.getExecutionDate());
    }
}
