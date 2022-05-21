package project.infra.rdb.stockexecution.entity;

import org.springframework.data.jpa.domain.Specification;
import project.infra.rdb.stockexecution.BuySellType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stock_execution_view")
public class StockExecutionView extends StockExecutionViewBase implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * 引数の日付 >= 約定日 を満たすデータを取得するSpecificationを返す
     * @param data
     * @return
     */
    public static Specification<StockExecutionView> executionDateAfter(LocalDate data){
        return data == null ? null : (root,query,builder) -> builder.greaterThanOrEqualTo(root.get(StockExecutionView_.executionDate.getName()), data);
    }

    /**
     * 引数の日付 <= 約定日 を満たすデータを取得するSpecificationを返す
     * @param data
     * @return
     */
    public static Specification<StockExecutionView> executionDateBefore(LocalDate data){
        return data == null ? null : (root,query,builder) -> builder.lessThanOrEqualTo(root.get(StockExecutionView_.executionDate.getName()), data);
    }

    /**
     * 特定のユーザのデータを取得するSpecificationを返す
     * @param portfolioIds
     * @return
     */
    public static Specification<StockExecutionView> isOwnUser(List<Long> portfolioIds){
        return portfolioIds == null? null : (root, query, builder) ->
                root.get(StockExecutionView_.stockPortfolioId.getName()).in(portfolioIds);
    }

    /**
     * 特定のユーザのデータを取得するSpecificationを返す
     * @param portfolioId
     * @return
     */
    public static Specification<StockExecutionView> equalsTo(Long portfolioId){
        return portfolioId == null? null : (root, query, builder) ->
                builder.equal(root.get(StockExecutionView_.stockPortfolioId.getName()), portfolioId);
    }

    public StockExecutionView(){}

    public StockExecutionView(long stockExecutionId, long stockPortfolioId, String portfolioName, String stockName, String currencyCode, BigDecimal bookValue, LocalDate executionDate, LocalDate valueDate, BuySellType buySellType, BigDecimal amount, boolean isExecutionDeleted, boolean isPortfolioDeleted) {
        super(stockExecutionId, stockPortfolioId, portfolioName, stockName, currencyCode, bookValue, executionDate, valueDate, buySellType, amount, isExecutionDeleted, isPortfolioDeleted);
    }
}
