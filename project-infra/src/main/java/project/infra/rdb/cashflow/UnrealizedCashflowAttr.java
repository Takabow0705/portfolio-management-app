package project.infra.rdb.cashflow;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "unrealized_cashflow_attr")
@Entity
public class UnrealizedCashflowAttr extends UnrealizedCashflowAttrBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
