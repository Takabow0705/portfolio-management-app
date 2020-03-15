package project.calculator.domain.repository.master.discountFactor;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DiscountFactor implements Comparable<DiscountFactor>, Serializable {
    private static final long serialVersionUID = 1L;
    private Double term;
    private BigDecimal discountFactor;

    @Override
    public int compareTo(DiscountFactor o) {
        return term.compareTo(o.getTerm());
    }
}
