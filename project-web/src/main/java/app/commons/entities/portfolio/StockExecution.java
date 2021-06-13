package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.StockExecutionBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stock_execution")
public class StockExecution extends StockExecutionBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
