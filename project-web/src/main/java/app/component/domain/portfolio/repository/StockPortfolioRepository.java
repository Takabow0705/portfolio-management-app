package app.component.domain.portfolio.repository;

import app.commons.entities.portfolio.StockPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Long> {

    public List<StockPortfolio> findByUserId(Long userId);
}
