package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.OwnedStockBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "owned_stock")
public class OwnedStock extends OwnedStockBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
