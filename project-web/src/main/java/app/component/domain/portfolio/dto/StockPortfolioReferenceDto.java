package app.component.domain.portfolio.dto;

import project.infra.rdb.stockportfolio.StockPortfolio;

public class StockPortfolioReferenceDto {

    private long id;

    private long userId;

    private String portfolioName;

    private String details;

    private boolean isDeleted;

    public StockPortfolioReferenceDto() {}

    public static StockPortfolioReferenceDto createFrom(StockPortfolio stockPortfolio){
        return new StockPortfolioReferenceDto(
                stockPortfolio.getId()
                ,stockPortfolio.getUserId()
                ,stockPortfolio.getPortfolioName()
                ,stockPortfolio.getDetails()
                ,stockPortfolio.isDeleted()
        );
    }

    public StockPortfolioReferenceDto(long id, long userId, String portfolioName, String details, boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.portfolioName = portfolioName;
        this.details = details;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "StockPortfolioReferenceDto{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
