package project.calculator.domain.finance.bond.external.grpc;


import io.grpc.finance.calculation.pricer.bond.BondPriceCalculatorGrpcServiceGrpc;
import io.grpc.finance.calculation.product.bond.BondPricingRequest;
import io.grpc.finance.calculation.product.bond.BondTheoreticalPriceResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import project.calculator.data.BondPricingData;
import project.calculator.data.converter.BondRequestConverter;
import project.calculator.data.response.CalculationResult;
import project.calculator.domain.finance.bond.service.calculator.pricing.BondPriceCalculatorService;

/**
 * 債券理論価格の計算をRPCで行うサービスクラス。
 */
@GRpcService
public class BondPriceCalculatorGrpcServiceImpl extends BondPriceCalculatorGrpcServiceGrpc.BondPriceCalculatorGrpcServiceImplBase {

    private final BondPriceCalculatorService bondPriceCalculatorService;

    public BondPriceCalculatorGrpcServiceImpl(BondPriceCalculatorService bondPriceCalculatorService) {
        this.bondPriceCalculatorService = bondPriceCalculatorService;
    }

    @Override
    public void responseTheoreticalPriceByDiscountFactor(BondPricingRequest request, StreamObserver<BondTheoreticalPriceResponse> responseObserver) {
        BondPricingData data = BondRequestConverter.convertFrom(request);
        CalculationResult result = this.bondPriceCalculatorService.calculateByDisCountFactor(data);
        BondTheoreticalPriceResponse response = BondTheoreticalPriceResponse.newBuilder()
                .setTheoreticalPrice(result.getResult().toPlainString())
                .setStatusMsg(result.getResultDetail()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
