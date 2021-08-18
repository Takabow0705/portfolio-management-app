package project.infra.rdb.stockportfolioevaluation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@IdClass(StockPortfolioEvaluationBase.PK.class)
public class StockPortfolioEvaluationBase implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "stock_portfolio_id")
    private long stockPortfolioId;

    @Column(name = "stock_code")
    @Id
    private String stockCode;

    @Id
    @Column(name = "base_date")
    private LocalDate baseDate;

    @Column(name = "current_value")
    private BigDecimal currentValue;

    @Column(name = "book_value")
    @NotNull
    private BigDecimal bookValue;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "current_pl")
    private BigDecimal currentPl;

    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;

    @Column(name = "is_lock_out")
    @NotNull
    private boolean isLockOut;

    @Column(name = "evaluation_data_base_date")
    private LocalDate evaluationDateBaseDate;

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

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentPl() {
        return currentPl;
    }

    public void setCurrentPl(BigDecimal currentPl) {
        this.currentPl = currentPl;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public BigDecimal getBookValue() {
        return bookValue;
    }

    public void setBookValue(BigDecimal bookValue) {
        this.bookValue = bookValue;
    }

    public boolean isLockOut() {
        return isLockOut;
    }

    public void setLockOut(boolean lockOut) {
        isLockOut = lockOut;
    }

    public LocalDate getEvaluationDateBaseDate() {
        return evaluationDateBaseDate;
    }

    public void setEvaluationDateBaseDate(LocalDate evaluationDateBaseDate) {
        this.evaluationDateBaseDate = evaluationDateBaseDate;
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

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(LocalDate baseDate) {
        this.baseDate = baseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPortfolioEvaluationBase that = (StockPortfolioEvaluationBase) o;
        return stockPortfolioId == that.stockPortfolioId && Objects.equals(stockCode, that.stockCode) && Objects.equals(baseDate, that.baseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockPortfolioId, stockCode, baseDate);
    }

    @Embeddable
    public static class PK implements Serializable {
        private static final long serialVersionUID = 1l;
        @Column(name = "stock_portfolio_id")
        private long stockPortfolioId;
        @Column(name = "stock_code")
        private String stockCode;
        @Column(name = "base_date")
        private LocalDate baseDate;

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

        public LocalDate getBaseDate() {
            return baseDate;
        }

        public void setBaseDate(LocalDate baseDate) {
            this.baseDate = baseDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return stockPortfolioId == pk.stockPortfolioId && stockCode == pk.stockCode && baseDate.equals(pk.baseDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stockPortfolioId, stockCode, baseDate);
        }
    }
}
