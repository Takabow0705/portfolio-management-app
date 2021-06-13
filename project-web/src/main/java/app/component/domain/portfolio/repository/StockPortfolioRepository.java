package app.component.domain.portfolio.repository;

import app.commons.entities.portfolio.StockPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Long> {

    public List<StockPortfolio> findByUserId(Long userId);
}
