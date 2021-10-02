package project.infra.rdb.stockportfolio;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "stock_portfolio")
public class StockPortfolio extends StockPortfolioBase implements Serializable {
    private static final long serialVersionUID = 1l;

}
