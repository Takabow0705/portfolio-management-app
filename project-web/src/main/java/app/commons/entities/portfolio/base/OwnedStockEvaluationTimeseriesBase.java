package app.commons.entities.portfolio.base;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class OwnedStockEvaluationTimeseriesBase implements Serializable{
    private static final long serialVersionUID = 1l;

    @EmbeddedId
    private OwnedStockEvaluationTimeseriesBase.PK pk;

    @Column(name = "current_value")
    private BigDecimal currentValue;

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

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
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
        OwnedStockEvaluationTimeseriesBase that = (OwnedStockEvaluationTimeseriesBase) o;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }

    @Embeddable
    public  static class PK implements Serializable {
        private static final long serialVersionUID = 1l;

        @Column(name = "owned_stock_id")
        private long ownedStockId;
        @Column(name = "evaluation_date")
        private LocalDate evaluationDate;

        public long getOwnedStockId() {
            return ownedStockId;
        }

        public void setOwnedStockId(long ownedStockId) {
            this.ownedStockId = ownedStockId;
        }

        public LocalDate getEvaluationDate() {
            return evaluationDate;
        }

        public void setEvaluationDate(LocalDate evaluationDate) {
            this.evaluationDate = evaluationDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return ownedStockId == pk.ownedStockId &&
                    evaluationDate.equals(pk.evaluationDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ownedStockId, evaluationDate);
        }
    }
}
