package project.calculator.domain.finance.bond.algo;

import java.math.BigDecimal;

/**
 * 計算アルゴリズムを抽象化するインターフェース
 * 型引数は計算に使用するデータオブジェクトの型。
 */
@FunctionalInterface
public interface CalculationStrategy<T> {
    public abstract BigDecimal execute(T data);
}
