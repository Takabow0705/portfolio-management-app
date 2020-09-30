package project.calculator.domain.repository.master.discountFactor;

import java.io.Serializable;
import java.math.BigDecimal;

public class DiscountFactor implements Comparable<DiscountFactor>, Serializable {
    private static final long serialVersionUID = 1L;
    private Double term;
    private BigDecimal discountFactor;

    public DiscountFactor() {
    }

    @Override
    public int compareTo(DiscountFactor o) {
        return term.compareTo(o.getTerm());
    }

    public Double getTerm() {
        return this.term;
    }

    public BigDecimal getDiscountFactor() {
        return this.discountFactor;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public void setDiscountFactor(BigDecimal discountFactor) {
        this.discountFactor = discountFactor;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DiscountFactor)) return false;
        final DiscountFactor other = (DiscountFactor) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$term = this.getTerm();
        final Object other$term = other.getTerm();
        if (this$term == null ? other$term != null : !this$term.equals(other$term)) return false;
        final Object this$discountFactor = this.getDiscountFactor();
        final Object other$discountFactor = other.getDiscountFactor();
        if (this$discountFactor == null ? other$discountFactor != null : !this$discountFactor.equals(other$discountFactor))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DiscountFactor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $term = this.getTerm();
        result = result * PRIME + ($term == null ? 43 : $term.hashCode());
        final Object $discountFactor = this.getDiscountFactor();
        result = result * PRIME + ($discountFactor == null ? 43 : $discountFactor.hashCode());
        return result;
    }

    public String toString() {
        return "DiscountFactor(term=" + this.getTerm() + ", discountFactor=" + this.getDiscountFactor() + ")";
    }
}
