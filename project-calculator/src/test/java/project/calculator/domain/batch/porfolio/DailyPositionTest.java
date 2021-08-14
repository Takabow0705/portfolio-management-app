package project.calculator.domain.batch.porfolio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.infra.rdb.stockexecution.BuySellType;
import project.infra.rdb.stockexecution.entity.StockExecution;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

class DailyPositionTest {

    private final BigDecimal ERROR = BigDecimal.valueOf(0.0000000001);
    @Test
    @DisplayName("該当基準日に約定が存在しない場合のテスト")
    public void generateInstanceTest1(){
        LocalDate baseDate = LocalDate.of(2021, 4,1);

        // 約定のデータ
        String stockCode = "8888";
        String currencyCode = "JPY";
        long stockPortfolioId = 1l;

        StockExecution execution1 = new StockExecution();
        execution1.setStockCode(stockCode);
        execution1.setStockPortfolioId(stockPortfolioId);
        execution1.setExecutionDate(LocalDate.of(2021,3,31));
        execution1.setAmount(BigDecimal.valueOf(1000));
        execution1.setBookValue(BigDecimal.valueOf(120));
        execution1.setCurrencyCode(currencyCode);
        execution1.setBuySellType(BuySellType.BUY);

        StockExecution execution2 = new StockExecution();
        execution2.setStockCode(stockCode);
        execution2.setStockPortfolioId(stockPortfolioId);
        execution2.setExecutionDate(LocalDate.of(2021,3,30));
        execution2.setAmount(BigDecimal.valueOf(1000));
        execution2.setBookValue(BigDecimal.valueOf(120));
        execution2.setCurrencyCode(currencyCode);
        execution2.setBuySellType(BuySellType.BUY);

        List<StockExecution> executions = Arrays.asList(execution1, execution2);
        DailyPosition target = DailyPosition.aggregateDailyExecution(baseDate, executions);

        Assertions.assertEquals(target.getLongAmount(), BigDecimal.ZERO);
        Assertions.assertEquals(target.getLongValue(), BigDecimal.ZERO);
        Assertions.assertEquals(target.getShortAmount(), BigDecimal.ZERO);
        Assertions.assertEquals(target.getShortAmount(), BigDecimal.ZERO);
    }

    @Test
    @DisplayName("該当基準日に1買約定のみの場合のテスト")
    public void generateInstanceTest2(){
        LocalDate baseDate = LocalDate.of(2021, 3,31);

        // 約定のデータ
        String stockCode = "8888";
        String currencyCode = "JPY";
        long stockPortfolioId = 1l;

        StockExecution execution1 = new StockExecution();
        execution1.setStockCode(stockCode);
        execution1.setStockPortfolioId(stockPortfolioId);
        execution1.setExecutionDate(LocalDate.of(2021,3,31));
        execution1.setAmount(BigDecimal.valueOf(1000));
        execution1.setBookValue(BigDecimal.valueOf(120));
        execution1.setCurrencyCode(currencyCode);
        execution1.setBuySellType(BuySellType.BUY);

        StockExecution execution2 = new StockExecution();
        execution2.setStockCode(stockCode);
        execution2.setStockPortfolioId(stockPortfolioId);
        execution2.setExecutionDate(LocalDate.of(2021,3,30));
        execution2.setAmount(BigDecimal.valueOf(100));
        execution2.setBookValue(BigDecimal.valueOf(150));
        execution2.setCurrencyCode(currencyCode);
        execution2.setBuySellType(BuySellType.BUY);

        List<StockExecution> executions = Arrays.asList(execution1, execution2);
        DailyPosition target = DailyPosition.aggregateDailyExecution(baseDate, executions);

        assertThat(target.getLongAmount(), closeTo(BigDecimal.valueOf(1000), ERROR));
        assertThat(target.getLongValue(), closeTo(BigDecimal.valueOf(120), ERROR));
        assertThat(target.getShortAmount(), closeTo(BigDecimal.ZERO, ERROR));
        assertThat(target.getShortAmount(), closeTo(BigDecimal.ZERO, ERROR));
    }
    @Test
    @DisplayName("該当基準日に売と買にそれぞれ1約定のみの場合のテスト")
    public void generateInstanceTest3(){
        LocalDate baseDate = LocalDate.of(2021, 3,31);

        // 約定のデータ
        String stockCode = "8888";
        String currencyCode = "JPY";
        long stockPortfolioId = 1l;

        StockExecution execution1 = new StockExecution();
        execution1.setStockCode(stockCode);
        execution1.setStockPortfolioId(stockPortfolioId);
        execution1.setExecutionDate(LocalDate.of(2021,3,31));
        execution1.setAmount(BigDecimal.valueOf(1000));
        execution1.setBookValue(BigDecimal.valueOf(120));
        execution1.setCurrencyCode(currencyCode);
        execution1.setBuySellType(BuySellType.BUY);

        StockExecution execution2 = new StockExecution();
        execution2.setStockCode(stockCode);
        execution2.setStockPortfolioId(stockPortfolioId);
        execution2.setExecutionDate(LocalDate.of(2021,3,31));
        execution2.setAmount(BigDecimal.valueOf(100));
        execution2.setBookValue(BigDecimal.valueOf(150));
        execution2.setCurrencyCode(currencyCode);
        execution2.setBuySellType(BuySellType.SELL);

        List<StockExecution> executions = Arrays.asList(execution1, execution2);
        DailyPosition target = DailyPosition.aggregateDailyExecution(baseDate, executions);

        assertThat(target.getLongAmount(), closeTo(BigDecimal.valueOf(1000), ERROR));
        assertThat(target.getLongValue(), closeTo(BigDecimal.valueOf(120), ERROR));
        assertThat(target.getShortAmount(), closeTo(BigDecimal.valueOf(-100), ERROR));
        assertThat(target.getShortValue(), closeTo(BigDecimal.valueOf(150), ERROR));
    }
    @Test
    @DisplayName("該当基準日に売1と買2の場合のテスト")
    public void generateInstanceTest4(){
        LocalDate baseDate = LocalDate.of(2021, 3,31);

        // 約定のデータ
        String stockCode = "8888";
        String currencyCode = "JPY";
        long stockPortfolioId = 1l;

        StockExecution execution1 = new StockExecution();
        execution1.setStockCode(stockCode);
        execution1.setStockPortfolioId(stockPortfolioId);
        execution1.setExecutionDate(LocalDate.of(2021,3,31));
        execution1.setAmount(BigDecimal.valueOf(1000));
        execution1.setBookValue(BigDecimal.valueOf(120));
        execution1.setCurrencyCode(currencyCode);
        execution1.setBuySellType(BuySellType.BUY);

        StockExecution execution2 = new StockExecution();
        execution2.setStockCode(stockCode);
        execution2.setStockPortfolioId(stockPortfolioId);
        execution2.setExecutionDate(LocalDate.of(2021,3,31));
        execution2.setAmount(BigDecimal.valueOf(100));
        execution2.setBookValue(BigDecimal.valueOf(150));
        execution2.setCurrencyCode(currencyCode);
        execution2.setBuySellType(BuySellType.SELL);

        StockExecution execution3 = new StockExecution();
        execution3.setStockCode(stockCode);
        execution3.setStockPortfolioId(stockPortfolioId);
        execution3.setExecutionDate(LocalDate.of(2021,3,31));
        execution3.setAmount(BigDecimal.valueOf(2000));
        execution3.setBookValue(BigDecimal.valueOf(110));
        execution3.setCurrencyCode(currencyCode);
        execution3.setBuySellType(BuySellType.BUY);

        List<StockExecution> executions = Arrays.asList(execution1, execution2, execution3);
        DailyPosition target = DailyPosition.aggregateDailyExecution(baseDate, executions);

        assertThat(target.getLongAmount(), closeTo(BigDecimal.valueOf(3000), ERROR));
        assertThat(target.getLongValue(), closeTo(BigDecimal.valueOf(113.3333333333), ERROR));
        assertThat(target.getShortAmount(), closeTo(BigDecimal.valueOf(-100), ERROR));
        assertThat(target.getShortValue(), closeTo(BigDecimal.valueOf(150), ERROR));
    }

    @Test
    @DisplayName("該当基準日に売2と買2の場合のテスト")
    public void generateInstanceTest5(){
        LocalDate baseDate = LocalDate.of(2021, 3,31);

        // 約定のデータ
        String stockCode = "8888";
        String currencyCode = "JPY";
        long stockPortfolioId = 1l;

        StockExecution execution1 = new StockExecution();
        execution1.setStockCode(stockCode);
        execution1.setStockPortfolioId(stockPortfolioId);
        execution1.setExecutionDate(LocalDate.of(2021,3,31));
        execution1.setAmount(BigDecimal.valueOf(1000));
        execution1.setBookValue(BigDecimal.valueOf(120));
        execution1.setCurrencyCode(currencyCode);
        execution1.setBuySellType(BuySellType.BUY);

        StockExecution execution2 = new StockExecution();
        execution2.setStockCode(stockCode);
        execution2.setStockPortfolioId(stockPortfolioId);
        execution2.setExecutionDate(LocalDate.of(2021,3,31));
        execution2.setAmount(BigDecimal.valueOf(100));
        execution2.setBookValue(BigDecimal.valueOf(150));
        execution2.setCurrencyCode(currencyCode);
        execution2.setBuySellType(BuySellType.SELL);

        StockExecution execution3 = new StockExecution();
        execution3.setStockCode(stockCode);
        execution3.setStockPortfolioId(stockPortfolioId);
        execution3.setExecutionDate(LocalDate.of(2021,3,31));
        execution3.setAmount(BigDecimal.valueOf(2000));
        execution3.setBookValue(BigDecimal.valueOf(110));
        execution3.setCurrencyCode(currencyCode);
        execution3.setBuySellType(BuySellType.BUY);

        StockExecution execution4 = new StockExecution();
        execution4.setStockCode(stockCode);
        execution4.setStockPortfolioId(stockPortfolioId);
        execution4.setExecutionDate(LocalDate.of(2021,3,31));
        execution4.setAmount(BigDecimal.valueOf(200));
        execution4.setBookValue(BigDecimal.valueOf(150));
        execution4.setCurrencyCode(currencyCode);
        execution4.setBuySellType(BuySellType.SELL);

        List<StockExecution> executions = Arrays.asList(execution1, execution2, execution3, execution4);
        DailyPosition target = DailyPosition.aggregateDailyExecution(baseDate, executions);

        assertThat(target.getLongAmount(), closeTo(BigDecimal.valueOf(3000), ERROR));
        assertThat(target.getLongValue(), closeTo(BigDecimal.valueOf(113.3333333333), ERROR));
        assertThat(target.getShortAmount(), closeTo(BigDecimal.valueOf(-300), ERROR));
        assertThat(target.getShortValue(), closeTo(BigDecimal.valueOf(150), ERROR));
    }
}