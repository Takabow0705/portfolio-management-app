package project.infra.rdb.stockexecution.entity;


import project.infra.rdb.stockexecution.BuySellType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class StockExecutionViewBase implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "stock_execution_id")
    private long stockExecutionId;

    @Column(name = "stock_portfolio_id")
    @NotNull
    private long stockPortfolioId;

    @Column(name = "portfolio_name")
    @NotNull
    private String portfolioName;

    @Column(name = "stock_name")
    @NotNull
    private String stockName;

    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;

    @Column(name = "book_value")
    @NotNull
    private BigDecimal bookValue;

    @Column(name = "execution_date")
    private LocalDate executionDate;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @Column(name = "buy_sell_type")
    @Enumerated(EnumType.STRING)
    private BuySellType buySellType;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "is_execution_deleted")
    @NotNull
    private boolean isExecutionDeleted;

    @Column(name = "is_portfolio_deleted")
    @NotNull
    private boolean isPortfolioDeleted;

    public StockExecutionViewBase(){}

    public StockExecutionViewBase(long stockExecutionId, long stockPortfolioId, String portfolioName, String stockName, String currencyCode, BigDecimal bookValue, LocalDate executionDate, LocalDate valueDate, BuySellType buySellType, BigDecimal amount, boolean isExecutionDeleted, boolean isPortfolioDeleted) {
        this.stockExecutionId = stockExecutionId;
        this.stockPortfolioId = stockPortfolioId;
        this.portfolioName = portfolioName;
        this.stockName = stockName;
        this.currencyCode = currencyCode;
        this.bookValue = bookValue;
        this.executionDate = executionDate;
        this.valueDate = valueDate;
        this.buySellType = buySellType;
        this.amount = amount;
        this.isExecutionDeleted = isExecutionDeleted;
        this.isPortfolioDeleted = isPortfolioDeleted;
    }

    public long getStockExecutionId() {
        return stockExecutionId;
    }

    public void setStockExecutionId(long stockExecutionId) {
        this.stockExecutionId = stockExecutionId;
    }

    public long getStockPortfolioId() {
        return stockPortfolioId;
    }

    public void setStockPortfolioId(long stockPortfolioId) {
        this.stockPortfolioId = stockPortfolioId;
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

    public boolean isPortfolioDeleted() {
        return isPortfolioDeleted;
    }

    public void setPortfolioDeleted(boolean portfolioDeleted) {
        isPortfolioDeleted = portfolioDeleted;
    }

    @Override
    public String toString() {
        return "StockExecutionViewBase{" +
                "stockExecutionId=" + stockExecutionId +
                ", stockPortfolioId=" + stockPortfolioId +
                ", portfolioName='" + portfolioName + '\'' +
                ", stockName='" + stockName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", bookValue=" + bookValue +
                ", executionDate=" + executionDate +
                ", valueDate=" + valueDate +
                ", buySellType=" + buySellType +
                ", amount=" + amount +
                ", isExecutionDeleted=" + isExecutionDeleted +
                ", isPortfolioDeleted=" + isPortfolioDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockExecutionViewBase that = (StockExecutionViewBase) o;
        return stockExecutionId == that.stockExecutionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockExecutionId);
    }
}
