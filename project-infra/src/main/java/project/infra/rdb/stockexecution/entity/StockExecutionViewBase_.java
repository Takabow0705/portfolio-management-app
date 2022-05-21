package project.infra.rdb.stockexecution.entity;

import project.infra.rdb.stockexecution.BuySellType;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDate;


@StaticMetamodel(StockExecutionViewBase.class)
public class StockExecutionViewBase_ {
    public static volatile SingularAttribute<StockExecutionViewBase, Long> stockExecutionId;
    public static volatile SingularAttribute<StockExecutionViewBase, Long> stockPortfolioId;
    public static volatile SingularAttribute<StockExecutionViewBase, String> portfolioName;
    public static volatile SingularAttribute<StockExecutionViewBase, String> stockName;
    public static volatile SingularAttribute<StockExecutionViewBase, String> currencyCode;
    public static volatile SingularAttribute<StockExecutionViewBase, BigDecimal> bookValue;
    public static volatile SingularAttribute<StockExecutionViewBase, LocalDate> executionDate;
    public static volatile SingularAttribute<StockExecutionViewBase, LocalDate> valueDate;
    public static volatile SingularAttribute<StockExecutionViewBase, BuySellType> buySellType;
    public static volatile SingularAttribute<StockExecutionViewBase, BigDecimal> amount;
    public static volatile SingularAttribute<StockExecutionViewBase, Boolean> isExecutionDeleted;
    public static volatile SingularAttribute<StockExecutionViewBase, Boolean> isPortfolioDeleted;
}
