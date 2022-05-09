package project.infra.rdb.cashflow;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "unrealized_cashflow")
@Entity
public class UnrealizedCashflow extends UnrealizedCashflowBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
