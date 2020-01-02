package app.commons.entities.products.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BondMasterPK implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @Column(name = "BOND_CODE")
    private String bondCode;
    @Id
    @Column(name = "VERSION")
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BondMasterPK that = (BondMasterPK) o;
        return Objects.equals(bondCode, that.bondCode) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bondCode, version);
    }
}
