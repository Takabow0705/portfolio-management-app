package app.commons.entities.portfolio.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class OwnedStockBase implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "stock_portfolios_id")
    private long stockPortfolioId;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "book_value")
    private BigDecimal bookValue;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "start_hold_date")
    private LocalDate startHoldDate;

    @Column(name = "end_hold_date")
    private LocalDate endHoldDate;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public long getStockPortfolioId() {
        return stockPortfolioId;
    }

    public void setStockPortfolioId(long stockPortfolioId) {
        this.stockPortfolioId = stockPortfolioId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getBookValue() {
        return bookValue;
    }

    public void setBookValue(BigDecimal bookValue) {
        this.bookValue = bookValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartHoldDate() {
        return startHoldDate;
    }

    public void setStartHoldDate(LocalDate startHoldDate) {
        this.startHoldDate = startHoldDate;
    }

    public LocalDate getEndHoldDate() {
        return endHoldDate;
    }

    public void setEndHoldDate(LocalDate endHoldDate) {
        this.endHoldDate = endHoldDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedStockBase that = (OwnedStockBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
