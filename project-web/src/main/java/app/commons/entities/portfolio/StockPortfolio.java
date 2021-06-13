package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.StockPortfolioBase;
import app.component.domain.portfolio.dto.StockPortfolioReferenceDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "stock_portfolio")
public class StockPortfolio extends StockPortfolioBase implements Serializable {
    private static final long serialVersionUID = 1l;

    public StockPortfolioReferenceDto createFrom(){
        return new StockPortfolioReferenceDto(
                this.getId()
                ,this.getUserId()
                ,this.getPortfolioName()
                ,this.getDetails()
                ,this.isDeleted()
        );
    }
}
