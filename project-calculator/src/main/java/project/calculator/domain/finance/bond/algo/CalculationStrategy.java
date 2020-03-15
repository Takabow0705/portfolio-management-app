package project.calculator.domain.finance.bond.algo;

import project.calculator.data.response.CalculationResult;

/**
 * 計算アルゴリズムを抽象化するインターフェース
 * 型引数は計算に使用するデータオブジェクトの型。
 */
@FunctionalInterface
public interface CalculationStrategy<T> {
    public abstract CalculationResult execute(T data);
}
