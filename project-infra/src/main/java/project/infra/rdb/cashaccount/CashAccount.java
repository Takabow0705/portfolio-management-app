package project.infra.rdb.cashaccount;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cash_balance")
public class CashAccount extends CashAccountBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
