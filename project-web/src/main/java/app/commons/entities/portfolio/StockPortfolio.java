package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.StockPortfolioBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "stock_portfolio")
public class StockPortfolio extends StockPortfolioBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
