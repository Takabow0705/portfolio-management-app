package project.infra.rdb.cashbalance;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@IdClass(CashBalanceBase.PK.class)
public class CashBalanceBase implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @Column(name = "cash_account_id")
    private long cashAccountId;
    @Id
    @Column(name = "base_date")
    private LocalDate baseDate;
    @Id
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;
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

    public long getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(long cashAccountId) {
        this.cashAccountId = cashAccountId;
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(LocalDate baseDate) {
        this.baseDate = baseDate;
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

    @Embeddable
    public static class PK implements Serializable{
        private static final long serialVersionUID = 1l;
        @Id
        @Column(name = "cash_account_id")
        private long cashAccountId;
        @Id
        @Column(name = "base_date")
        private LocalDate baseDate;
        @Id
        @Column(name = "currency_code")
        private String currencyCode;

        public long getCashAccountId() {
            return cashAccountId;
        }

        public void setCashAccountId(long cashAccountId) {
            this.cashAccountId = cashAccountId;
        }

        public LocalDate getBaseDate() {
            return baseDate;
        }

        public void setBaseDate(LocalDate baseDate) {
            this.baseDate = baseDate;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return cashAccountId == pk.cashAccountId && baseDate.equals(pk.baseDate) && currencyCode.equals(pk.currencyCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cashAccountId, baseDate, currencyCode);
        }
    }
}
