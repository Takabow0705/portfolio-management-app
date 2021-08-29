package project.infra.rdb.stockexecution.entity;

import project.infra.rdb.stockexecution.BuySellType;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@StaticMetamodel(StockExecutionBase.class)
public class StockExecutionBase_ {
    public static volatile SingularAttribute<StockExecutionBase, Long> id;
    public static volatile SingularAttribute<StockExecutionBase, Long> stockPortfolioId;
    public static volatile SingularAttribute<StockExecutionBase, String> stockCode;
    public static volatile SingularAttribute<StockExecutionBase, BigDecimal> bookValue;
    public static volatile SingularAttribute<StockExecutionBase, String> currencyCode;
    public static volatile SingularAttribute<StockExecutionBase, BigDecimal> amount;
    public static volatile SingularAttribute<StockExecutionBase, LocalDate> executionDate;
    public static volatile SingularAttribute<StockExecutionBase, LocalDate> valueDate;
    public static volatile SingularAttribute<StockExecutionBase, BuySellType> buySellType;
    public static volatile SingularAttribute<StockExecutionBase, Boolean> isDeleted;
    public static volatile SingularAttribute<StockExecutionBase, Timestamp> updateTimestamp;
    public static volatile SingularAttribute<StockExecutionBase, String> updateUser;
    public static volatile SingularAttribute<StockExecutionBase, Timestamp> createTimestamp;
    public static volatile SingularAttribute<StockExecutionBase, String> createUser;
}
