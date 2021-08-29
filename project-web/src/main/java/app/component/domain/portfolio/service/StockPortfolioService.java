package app.component.domain.portfolio.service;

import app.component.domain.portfolio.dto.StockPortfolioDto;
import app.component.domain.portfolio.dto.PortfolioBasicStats;
import app.component.domain.portfolio.dto.StockPortfolioReferenceDto;

import java.util.List;

public interface StockPortfolioService {

    public abstract List<StockPortfolioReferenceDto> findByUserId(long userId);

    public abstract void save(StockPortfolioReferenceDto dto);

    public abstract void update(StockPortfolioReferenceDto dto);

    public abstract List<StockPortfolioDto> getPortfolioStockList(StockPortfolioReferenceDto dto);

    public abstract PortfolioBasicStats calculateWeeklyBasicStats(StockPortfolioReferenceDto dto);
}
