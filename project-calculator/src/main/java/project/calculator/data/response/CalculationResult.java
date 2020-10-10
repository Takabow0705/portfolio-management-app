package project.calculator.data.response;

import io.grpc.util.StatusMsg;
import project.calculator.data.enums.PricingMethod;

import java.io.Serializable;
import java.math.BigDecimal;

public final class CalculationResult implements Serializable {
    private static final Long serialVersionUID = 1L;
    private final BigDecimal result;
    private final PricingMethod method;
    private final StatusMsg resultDetail;

    private CalculationResult(BigDecimal result, PricingMethod method, StatusMsg resultDetail) {
        this.result = result;
        this.method = method;
        this.resultDetail = resultDetail;
    }

    public static CalculationResult create(BigDecimal result, PricingMethod method, StatusMsg resultDetail) {
        return new CalculationResult(result, method, resultDetail);
    }

    public BigDecimal getResult() {
        return this.result;
    }

    public PricingMethod getMethod() {
        return this.method;
    }

    public StatusMsg getResultDetail() {
        return this.resultDetail;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CalculationResult)) return false;
        final CalculationResult other = (CalculationResult) o;
        final Object this$result = this.getResult();
        final Object other$result = other.getResult();
        if (this$result == null ? other$result != null : !this$result.equals(other$result)) return false;
        final Object this$method = this.getMethod();
        final Object other$method = other.getMethod();
        if (this$method == null ? other$method != null : !this$method.equals(other$method)) return false;
        final Object this$resultDetail = this.getResultDetail();
        final Object other$resultDetail = other.getResultDetail();
        if (this$resultDetail == null ? other$resultDetail != null : !this$resultDetail.equals(other$resultDetail))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $result = this.getResult();
        result = result * PRIME + ($result == null ? 43 : $result.hashCode());
        final Object $method = this.getMethod();
        result = result * PRIME + ($method == null ? 43 : $method.hashCode());
        final Object $resultDetail = this.getResultDetail();
        result = result * PRIME + ($resultDetail == null ? 43 : $resultDetail.hashCode());
        return result;
    }

    public String toString() {
        return "CalculationResult(result=" + this.getResult() + ", method=" + this.getMethod() + ", resultDetail=" + this.getResultDetail() + ")";
    }
}
