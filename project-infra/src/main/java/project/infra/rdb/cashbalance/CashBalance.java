package project.infra.rdb.cashbalance;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "cash_balance")
@Entity
public class CashBalance extends CashBalanceBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
