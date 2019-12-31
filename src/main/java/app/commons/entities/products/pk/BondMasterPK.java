package app.commons.entities.products.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class BondMasterPK implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @Column(name = "BOND_CODE")
    private String bondCode;
    @Id
    @Column(name = "VERSION")
    private Long version;
}
