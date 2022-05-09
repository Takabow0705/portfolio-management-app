package project.infra.rdb.cashflow;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "unrealized_cashflow_history")
@Entity
public class CashflowHistory extends UnrealizedCashflowBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
