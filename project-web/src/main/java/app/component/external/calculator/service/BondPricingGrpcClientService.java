package app.component.external.calculator.service;

import app.commons.dto.BondDataDto;
import io.grpc.finance.calculation.product.bond.BondTheoreticalPriceResponse;

public interface BondPricingGrpcClientService {

    public abstract BondTheoreticalPriceResponse calculateBondPriceByDiscountFactor(BondDataDto data);
}
