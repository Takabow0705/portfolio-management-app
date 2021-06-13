package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.StockPortfolioEvaluationBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "stock_portfolio_evaluation")
public class StockPortfolioEvaluation extends StockPortfolioEvaluationBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
