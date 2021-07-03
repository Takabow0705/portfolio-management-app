package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.StockExecutionBase;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "stock_execution")
public class StockExecution extends StockExecutionBase implements Serializable {
    private static final long serialVersionUID = 1l;

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
     * @param userId
     * @return
     */
    public static Specification<StockExecution> isOwnUser(Long userId){
        return userId == null? null : (root, query, builder) -> builder.equal(root.get(StockExecution_.id.getName()), userId);
    }
}
