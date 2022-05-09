package app.presentation.inner.calculator.service;

import app.domain.calculation.bond.BondDataDto;
import app.domain.calculation.bond.PaymentTypeConverter;
import io.grpc.ManagedChannel;
import io.grpc.finance.calculation.product.bond.BondPricingRequest;
import io.grpc.finance.calculation.product.bond.BondTheoreticalPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static io.grpc.finance.calculation.pricer.bond.BondPriceCalculatorGrpcServiceGrpc.BondPriceCalculatorGrpcServiceBlockingStub;
import static io.grpc.finance.calculation.pricer.bond.BondPriceCalculatorGrpcServiceGrpc.newBlockingStub;

@Service
public class BondPricingGrpcClientServiceImpl implements BondPricingGrpcClientService{

    @Autowired
    @Qualifier("calculator")
    private ManagedChannel managedChannel;

    @Override
    public BondTheoreticalPriceResponse calculateBondPriceByDiscountFactor(BondDataDto data){
        BondPriceCalculatorGrpcServiceBlockingStub stub = newBlockingStub(managedChannel);
        BondPricingRequest request = BondPricingRequest.newBuilder()
                .setCouponRate(data.getCouponRate())
                .setIsTermEndPayment(data.getIsTermEndPayment())
                .setUnit(data.getUnit())
                .setCurrentMaturity(data.getCurrentMaturity())
                .setPaymentType(PaymentTypeConverter.covertFrom(data.getPaymentType()))
                .build();
        BondTheoreticalPriceResponse response = stub.responseTheoreticalPriceByDiscountFactor(request);
        return response;
    }
}
