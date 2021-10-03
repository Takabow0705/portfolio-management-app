package project.infra.rdb.cashflow;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class UnrealizedCashflowBase implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "base_data")
    @NotNull
    private LocalDate baseDate;
    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;
    @Column(name = "cash_account_id")
    @NotNull
    private long cashAccountId;
    @Column(name = "pay_or_receive")
    @Enumerated(EnumType.STRING)
    private CashFlowPayment payOrReceive;
    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;
    @Column(name = "deleted")
    @NotNull
    private boolean deleted;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "unrealized_cashflow_id")
    private UnrealizedCashflowAttr unrealizedCashflowAttr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(long cashAccountId) {
        this.cashAccountId = cashAccountId;
    }

    public CashFlowPayment getPayOrReceive() {
        return payOrReceive;
    }

    public void setPayOrReceive(CashFlowPayment payOrReceive) {
        this.payOrReceive = payOrReceive;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public UnrealizedCashflowAttr getUnrealizedCashflowAttr() {
        return unrealizedCashflowAttr;
    }

    public void setUnrealizedCashflowAttr(UnrealizedCashflowAttr unrealizedCashflowAttr) {
        this.unrealizedCashflowAttr = unrealizedCashflowAttr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnrealizedCashflowBase that = (UnrealizedCashflowBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
