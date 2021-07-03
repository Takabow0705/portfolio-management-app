package app.commons.entities.portfolio.execution;

import app.commons.enums.finance.BuySellType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class StockExecutionBase implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_execution_id_generator")
    @SequenceGenerator(name = "stock_execution_id_generator", sequenceName = "stock_execution_seq",allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "stock_portfolio_id")
    @NotNull
    private long stockPortfolioId;

    @Column(name = "stock_code")
    @NotNull
    private String stockCode;

    @Column(name = "book_value")
    @NotNull
    private BigDecimal bookValue;

    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "execution_date")
    private LocalDate executionDate;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @Column(name = "buy_sell_type")
    @Enumerated(EnumType.STRING)
    private BuySellType buySellType;

    @Column(name = "is_deleted")
    @NotNull
    private boolean isDeleted;

    @Column(name = "update_timestamp")
    @NotNull
    private Timestamp updateTimestamp;

    @Column(name = "update_user")
    @NotNull
    private String updateUser;

    @Column(name = "create_timestamp")
    @NotNull
    private Timestamp createTimestamp;

    @Column(name = "create_user")
    @NotNull
    private String createUser;

    public StockExecutionBase(){}
    public StockExecutionBase(long stockPortfolioId, String stockCode, BigDecimal bookValue, String currencyCode, BigDecimal amount, LocalDate executionDate, LocalDate valueDate, BuySellType buySellType, boolean isDeleted, Timestamp updateTimestamp, String updateUser, Timestamp createTimestamp, String createUser) {
        this.stockPortfolioId = stockPortfolioId;
        this.stockCode = stockCode;
        this.bookValue = bookValue;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.executionDate = executionDate;
        this.valueDate = valueDate;
        this.buySellType = buySellType;
        this.isDeleted = isDeleted;
        this.updateTimestamp = updateTimestamp;
        this.updateUser = updateUser;
        this.createTimestamp = createTimestamp;
        this.createUser = createUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public BuySellType getBuySellType() {
        return buySellType;
    }

    public void setBuySellType(BuySellType buySellType) {
        this.buySellType = buySellType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockExecutionBase that = (StockExecutionBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
