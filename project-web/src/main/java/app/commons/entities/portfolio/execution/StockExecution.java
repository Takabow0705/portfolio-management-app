package app.commons.entities.portfolio.execution;

import app.commons.enums.finance.BuySellType;
import app.commons.file.csv.StockExecutionRegistrationCsv;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stock_execution")
public class StockExecution extends StockExecutionBase implements Serializable {
    private static final long serialVersionUID = 1l;

    public static StockExecution createFrom(StockExecutionRegistrationCsv csv){
        return new StockExecution(
                Long.valueOf(csv.getStockPortfolioId())
                ,csv.getStockCode()
                ,csv.getBookValue()
                ,csv.getCurrencyCode()
                ,csv.getAmount()
                ,csv.getExecutionDate()
                ,csv.getValueDate()
                ,BuySellType.convertFrom(csv.getBuySellType())
                ,csv.getIsDeleted()
                ,Timestamp.valueOf(LocalDateTime.now())
                ,""
                ,Timestamp.valueOf(LocalDateTime.now())
                ,""
        );
    }
    /**
     * 引数の日付 >= 約定日 を満たすデータを取得するSpecificationを返す
     * @param data
     * @return
     */
    public static Specification<StockExecution> executionDateAfter(LocalDate data){
        return data == null ? null : (root,query,builder) -> builder.greaterThanOrEqualTo(root.get(StockExecution_.executionDate.getName()), data);
    }

    /**
     * 引数の日付 <= 約定日 を満たすデータを取得するSpecificationを返す
     * @param data
     * @return
     */
    public static Specification<StockExecution> executionDateBefore(LocalDate data){
        return data == null ? null : (root,query,builder) -> builder.lessThanOrEqualTo(root.get(StockExecution_.executionDate.getName()), data);
    }

    /**
     * 特定のユーザのデータを取得するSpecificationを返す
     * @param portfolioIds
     * @return
     */
    public static Specification<StockExecution> isOwnUser(List<Long> portfolioIds){
        return portfolioIds == null? null : (root, query, builder) ->
                root.get(StockExecution_.stockPortfolioId.getName()).in(portfolioIds);
    }

    public void setCreateUser(String userName){
        super.setCreateUser(userName);
    }

    public void setUpdateUser(String userName){
        super.setUpdateUser(userName);
    }

    public StockExecution(){}

    private StockExecution(long stockPortfolioId, String stockCode, BigDecimal bookValue, String currencyCode, BigDecimal amount, LocalDate executionDate, LocalDate valueDate, BuySellType buySellType, boolean isDeleted, Timestamp updateTimestamp, String updateUser, Timestamp createTimestamp, String createUser) {
     super(stockPortfolioId,stockCode,bookValue,currencyCode,amount,executionDate,valueDate,buySellType,isDeleted,updateTimestamp,updateUser,createTimestamp,createUser);
    }

}
