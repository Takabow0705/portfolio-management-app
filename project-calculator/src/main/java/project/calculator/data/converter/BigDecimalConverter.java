package project.calculator.data.converter;

import java.math.BigDecimal;

public class BigDecimalConverter {

    /**
     * <p>
     * 文字列のBigDecimal表現を返します。
     * </p>
     * @param s
     * @return
     */
    public static BigDecimal convertFrom(String s){
        return s == null || s.isEmpty() ? BigDecimal.ZERO : new BigDecimal(s);
    }
}
