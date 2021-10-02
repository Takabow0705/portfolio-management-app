package app.presentation.inner.calculator;

import app.presentation.inner.calculator.service.BondPricingGrpcClientService;

public interface CalculatorGrpcFacade {

    public abstract BondPricingGrpcClientService getBondPricingGrpcClientService();
}
