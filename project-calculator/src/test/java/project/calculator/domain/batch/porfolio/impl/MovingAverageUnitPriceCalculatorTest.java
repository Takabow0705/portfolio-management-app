package project.calculator.domain.batch.porfolio.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.calculator.domain.calendar.BusinessDays;
import project.infra.rdb.stockexecution.BuySellType;
import project.infra.rdb.stockexecution.entity.StockExecution;
import project.infra.rdb.strockpricetimeseries.StockPrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

class MovingAverageUnitPriceCalculatorTest {

    @Test
    @DisplayName("市場価格は4/1のみ存在し、以降のデータは横置評価になるケース")
    public void case1(){
        //Given
        List<StockExecution> executions = this.createTestData();
        BusinessDays businessDays = this.createBaseDate();
        List<StockPrice> marketData = new ArrayList<>();
    }

    private List<StockExecution> createTestData(){
        // 約定のデータ
        String stockCode = "8888";
        String currencyCode = "JPY";
        long stockPortfolioId = 1l;

        StockExecution execution1 = new StockExecution();
        execution1.setStockCode(stockCode);
        execution1.setStockPortfolioId(stockPortfolioId);
        execution1.setExecutionDate(LocalDate.of(2021,4,1));
        execution1.setAmount(BigDecimal.valueOf(100));
        execution1.setBookValue(BigDecimal.valueOf(120));
        execution1.setCurrencyCode(currencyCode);
        execution1.setBuySellType(BuySellType.BUY);

        StockExecution execution2 = new StockExecution();
        execution2.setStockCode(stockCode);
        execution2.setStockPortfolioId(stockPortfolioId);
        execution2.setExecutionDate(LocalDate.of(2021,4,2));
        execution2.setAmount(BigDecimal.valueOf(200));
        execution2.setBookValue(BigDecimal.valueOf(150));
        execution2.setCurrencyCode(currencyCode);
        execution2.setBuySellType(BuySellType.BUY);

        StockExecution execution3 = new StockExecution();
        execution3.setStockCode(stockCode);
        execution3.setStockPortfolioId(stockPortfolioId);
        execution3.setExecutionDate(LocalDate.of(2021,4,5));
        execution3.setAmount(BigDecimal.valueOf(100));
        execution3.setBookValue(BigDecimal.valueOf(130));
        execution3.setCurrencyCode(currencyCode);
        execution3.setBuySellType(BuySellType.SELL);

        StockExecution execution4 = new StockExecution();
        execution4.setStockCode(stockCode);
        execution4.setStockPortfolioId(stockPortfolioId);
        execution4.setExecutionDate(LocalDate.of(2021,4,6));
        execution4.setAmount(BigDecimal.valueOf(100));
        execution4.setBookValue(BigDecimal.valueOf(125));
        execution4.setCurrencyCode(currencyCode);
        execution4.setBuySellType(BuySellType.SELL);

        StockExecution execution5 = new StockExecution();
        execution5.setStockCode(stockCode);
        execution5.setStockPortfolioId(stockPortfolioId);
        execution5.setExecutionDate(LocalDate.of(2021,4,6));
        execution5.setAmount(BigDecimal.valueOf(100));
        execution5.setBookValue(BigDecimal.valueOf(120));
        execution5.setCurrencyCode(currencyCode);
        execution5.setBuySellType(BuySellType.BUY);

        StockExecution execution6 = new StockExecution();
        execution6.setStockCode(stockCode);
        execution6.setStockPortfolioId(stockPortfolioId);
        execution6.setExecutionDate(LocalDate.of(2021,4,7));
        execution6.setAmount(BigDecimal.valueOf(100));
        execution6.setBookValue(BigDecimal.valueOf(142));
        execution6.setCurrencyCode(currencyCode);
        execution6.setBuySellType(BuySellType.BUY);

        List<StockExecution> executions = Arrays.asList(execution1,execution2,execution3,execution4,execution5,execution6);
        return executions;
    }

    private BusinessDays createBaseDate(){
        LocalDate baseDate1 = LocalDate.of(2021,4,1);
        LocalDate baseDate2 = LocalDate.of(2021,4,2);
        LocalDate baseDate3 = LocalDate.of(2021,4,5);
        LocalDate baseDate4 = LocalDate.of(2021,4,6);
        LocalDate baseDate5 = LocalDate.of(2021,4,7);
        Set<LocalDate> dates = new HashSet<>(Arrays.asList(baseDate1, baseDate2, baseDate3, baseDate4, baseDate5));
        return BusinessDays.of(dates);
    }
}