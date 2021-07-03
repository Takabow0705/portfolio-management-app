package app.commons.file.csv;

import app.commons.entities.portfolio.execution.StockExecution;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@JsonPropertyOrder({
        "stock_portfolio_id"
        , "stock_code"
        , "book_value"
        , "currency_code"
        , "amount"
        , "execution_date"
        , "value_date"
        , "buy_sell_type"
        , "is_deleted"
})
public class StockExecutionOutputCsv {

    @JsonProperty("stock_portfolio_id")
    private long stockPortfolioId;

    @JsonProperty("stock_code")
    private String stockCode;

    @JsonProperty("book_value")
    private BigDecimal bookValue;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("execution_date")
    private String executionDate;

    @JsonProperty("value_date")
    private String valueDate;

    @JsonProperty("buy_sell_type")
    private String buySellType;

    @JsonProperty("is_deleted")
    private boolean isDeleted;

    public static StockExecutionOutputCsv createFrom(StockExecution execution) {
        return new StockExecutionOutputCsv(
                execution.getStockPortfolioId()
                , execution.getStockCode()
                , execution.getBookValue()
                , execution.getCurrencyCode()
                , execution.getAmount()
                , execution.getExecutionDate()
                , execution.getValueDate()
                , execution.getBuySellType().getType()
                , execution.isDeleted()
        );
    }

    public StockExecutionOutputCsv() {
    }

    public StockExecutionOutputCsv(long stockPortfolioId, String stockCode, BigDecimal bookValue, String currencyCode, BigDecimal amount, LocalDate executionDate, LocalDate valueDate, String buySellType, boolean isDeleted) {
        this.stockPortfolioId = stockPortfolioId;
        this.stockCode = stockCode;
        this.bookValue = bookValue;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.executionDate = executionDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.valueDate = valueDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.buySellType = buySellType;
        this.isDeleted = isDeleted;
    }

    public long getStockPortfolioId() {
        return stockPortfolioId;
    }

    public void setStockPortfolioId(long stockPortfolioId) {
        this.stockPortfolioId = stockPortfolioId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public BigDecimal getBookValue() {
        return bookValue;
    }

    public void setBookValue(BigDecimal bookValue) {
        this.bookValue = bookValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public String getBuySellType() {
        return buySellType;
    }

    public void setBuySellType(String buySellType) {
        this.buySellType = buySellType;
    }

    @JsonIgnore //削除フラグの項目が重複出力されるので指定する。
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockExecutionOutputCsv that = (StockExecutionOutputCsv) o;
        return stockPortfolioId == that.stockPortfolioId && buySellType == that.buySellType && isDeleted == that.isDeleted && Objects.equals(stockCode, that.stockCode) && Objects.equals(bookValue, that.bookValue) && Objects.equals(currencyCode, that.currencyCode) && Objects.equals(amount, that.amount) && Objects.equals(executionDate, that.executionDate) && Objects.equals(valueDate, that.valueDate);
    }

    public static String createFilename(LocalDate fromDate, LocalDate toDate) {
        if (fromDate != null && toDate != null) {
            return String.format("Stock_Execution_%s-%s.csv", fromDate, toDate);
        }
        return "Stock_Execution.csv";
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockPortfolioId, stockCode, bookValue, currencyCode, amount, executionDate, valueDate, buySellType, isDeleted);
    }

    @Override
    public String toString() {
        return "StockExecutionOutputCsv{" +
                "stockPortfolioId=" + stockPortfolioId +
                ", stockCode='" + stockCode + '\'' +
                ", bookValue=" + bookValue +
                ", currencyCode='" + currencyCode + '\'' +
                ", amount=" + amount +
                ", executionDate=" + executionDate +
                ", valueDate=" + valueDate +
                ", buySellType=" + buySellType +
                ", isDeleted=" + isDeleted +
                '}';
    }
}