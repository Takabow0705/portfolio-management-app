package project.infra.rdb.strockpricetimeseries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    @Query("select s from StockPrice s where :from <= s.baseDate and :to >= s.baseDate and s.stockCode in :stockCode and s.isDeleted = false")
    public List<StockPrice> retrieveByStockCode(@Param("stockCode") Set<String> stockCode, @Param("from") LocalDate startDate, @Param("to") LocalDate endDate);
}
