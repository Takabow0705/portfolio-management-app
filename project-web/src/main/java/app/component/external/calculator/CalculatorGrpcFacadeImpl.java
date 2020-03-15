package app.component.external.calculator;

import app.component.external.calculator.service.BondPricingGrpcClientService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class CalculatorGrpcFacadeImpl implements CalculatorGrpcFacade {

    private final BondPricingGrpcClientService bondPricingGrpcClientService;
}
