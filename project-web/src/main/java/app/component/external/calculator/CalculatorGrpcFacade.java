package app.component.external.calculator;

import app.component.external.calculator.service.BondPricingGrpcClientService;

public interface CalculatorGrpcFacade {

    public abstract BondPricingGrpcClientService getBondPricingGrpcClientService();
}
