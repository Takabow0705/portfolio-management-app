package app.commons.entities.pk;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class BondMasterPK implements Serializable {
    @Id
    @Column(name = "BOND_CODE")
    private String bondCode;
    @Id
    @Column(name = "VERSION")
    private Long version;

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BondMasterPK that = (BondMasterPK) o;

        return new EqualsBuilder()
                .append(bondCode, that.bondCode)
                .append(version, that.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(bondCode)
                .append(version)
                .toHashCode();
    }
}
