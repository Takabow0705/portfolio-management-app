package app.usecase.portfolio;

import app.domain.portfolio.StockPortfolioDto;
import app.domain.portfolio.PortfolioBasicStats;
import app.domain.portfolio.StockPortfolioReferenceDto;

import java.util.List;

public interface StockPortfolioService {

    public abstract List<StockPortfolioReferenceDto> findByUserId(long userId);

    public abstract void save(StockPortfolioReferenceDto dto);

    public abstract void update(StockPortfolioReferenceDto dto);

    public abstract List<StockPortfolioDto> getPortfolioStockList(StockPortfolioReferenceDto dto);

    public abstract PortfolioBasicStats calculateWeeklyBasicStats(StockPortfolioReferenceDto dto);
}
