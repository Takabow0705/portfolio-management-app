package app.component.domain.portfolio.repository;

import app.commons.entities.portfolio.StockPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPortfoliosRepository extends JpaRepository<StockPortfolio, Long> {
}
