package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.StockPortfoliosBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "stock_portfolios")
public class StockPortfolios extends StockPortfoliosBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
