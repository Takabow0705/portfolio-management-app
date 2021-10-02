package app.presentation.inner.calculator.service;

import app.domain.calculation.bond.BondDataDto;
import io.grpc.finance.calculation.product.bond.BondTheoreticalPriceResponse;

public interface BondPricingGrpcClientService {

    public abstract BondTheoreticalPriceResponse calculateBondPriceByDiscountFactor(BondDataDto data);
}
