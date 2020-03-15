package project.calculator.data.response;

import io.grpc.util.StatusMsg;
import lombok.Value;
import project.calculator.data.enums.PricingMethod;

import java.math.BigDecimal;

@Value(staticConstructor = "create")
public class CalculationResult {
    private final BigDecimal result;
    private final PricingMethod method;
    private final StatusMsg resultDetail;
}
