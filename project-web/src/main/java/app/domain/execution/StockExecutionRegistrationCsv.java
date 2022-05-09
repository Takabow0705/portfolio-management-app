package app.domain.execution;

import com.github.mygreen.supercsv.annotation.CsvBean;
import com.github.mygreen.supercsv.annotation.CsvColumn;
import com.github.mygreen.supercsv.annotation.format.CsvBooleanFormat;
import com.github.mygreen.supercsv.annotation.format.CsvDateTimeFormat;
import project.infra.rdb.stockexecution.BuySellType;
import project.infra.rdb.stockexecution.entity.StockExecution;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@CsvBean(header = true, validateHeader = true)
public class StockExecutionRegistrationCsv implements Serializable {

    @CsvColumn(number = 1, label = "stock_portfolio_id")
    private long stockPortfolioId;

    @CsvColumn(number = 2, label = "stock_code")
    private String stockCode;

    @CsvColumn(number = 3, label = "book_value")
    private BigDecimal bookValue;

    @CsvColumn(number = 4, label = "currency_code")
    private String currencyCode;

    @CsvColumn(number = 5, label = "amount")
    private BigDecimal amount;

    @CsvColumn(number = 6, label = "execution_date")
    @CsvDateTimeFormat
    private LocalDate executionDate;

    @CsvColumn(number = 7, label = "value_date")
    @CsvDateTimeFormat
    private LocalDate valueDate;

    @CsvColumn(number = 8, label = "buy_sell_type")
    private String buySellType;

    @CsvColumn(number = 9, label = "is_deleted")
    @CsvBooleanFormat(readForTrue = {"true", "1"}, readForFalse = {"false", "0"}, ignoreCase = true)
    private boolean isDeleted;

    public static StockExecution convertToExecution(StockExecutionRegistrationCsv csv){
        return new StockExecution(
                Long.valueOf(csv.getStockPortfolioId())
                ,csv.getStockCode()
                ,csv.getBookValue()
                ,csv.getCurrencyCode()
                ,csv.getAmount()
                ,csv.getExecutionDate()
                ,csv.getValueDate()
                ,BuySellType.convertFrom(csv.getBuySellType())
                ,csv.getIsDeleted()
                ,Timestamp.valueOf(LocalDateTime.now())
                ,""
                ,Timestamp.valueOf(LocalDateTime.now())
                ,""
        );
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

    public String getBuySellType() {
        return buySellType;
    }

    public void setBuySellType(String buySellType) {
        this.buySellType = buySellType;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockExecutionRegistrationCsv that = (StockExecutionRegistrationCsv) o;
        return stockPortfolioId == that.stockPortfolioId && buySellType == that.buySellType && isDeleted == that.isDeleted && Objects.equals(stockCode, that.stockCode) && Objects.equals(bookValue, that.bookValue) && Objects.equals(currencyCode, that.currencyCode) && Objects.equals(amount, that.amount) && Objects.equals(executionDate, that.executionDate) && Objects.equals(valueDate, that.valueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockPortfolioId, stockCode, bookValue, currencyCode, amount, executionDate, valueDate, buySellType, isDeleted);
    }
}
