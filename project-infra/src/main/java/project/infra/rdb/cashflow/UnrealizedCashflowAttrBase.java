package project.infra.rdb.cashflow;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
public class UnrealizedCashflowAttrBase implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "unrealized_cashflow_id")
    @NotNull
    private long unrealizedCashflowId;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "detail")
    private String detail;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUnrealizedCashflowId() {
        return unrealizedCashflowId;
    }

    public void setUnrealizedCashflowId(long unrealizedCashflowId) {
        this.unrealizedCashflowId = unrealizedCashflowId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnrealizedCashflowAttrBase that = (UnrealizedCashflowAttrBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
