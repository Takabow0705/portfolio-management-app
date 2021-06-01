package app.component.domain.portfolio.repository;

import app.commons.entities.portfolio.OwnedStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedStockRepository extends JpaRepository<OwnedStock, Long> {
}
