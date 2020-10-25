package app.component.service.pricer.bond;

import app.commons.dto.BondDataDto;
import app.component.external.calculator.CalculatorGrpcFacade;
import com.google.common.flogger.FluentLogger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.grpc.finance.calculation.product.bond.BondTheoreticalPriceResponse;
import io.grpc.util.Status;
import org.springframework.stereotype.Service;

@Service
public class BondPriceCalculationServiceImpl implements BondPriceCalculationService {

    private final CalculatorGrpcFacade facade;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public BondPriceCalculationServiceImpl(CalculatorGrpcFacade facade) {
        this.facade = facade;
    }

    @Override
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public BondDataDto calculateBondPriceByDiscountFactor(BondDataDto request){
        BondTheoreticalPriceResponse response = this.facade.getBondPricingGrpcClientService().calculateBondPriceByDiscountFactor(request);

        if (response == null){
            logger.atWarning().log("RPC結果がnullです。");
            return null;
        }

        if(response.getStatusMsg().getStatus() != Status.OK){
            logger.atWarning().log("RPC結果が不正です。処理が正常終了していない可能性があります。");
            return null;
        }

        request.setTheoreticalPrice(response.getTheoreticalPrice());
        logger.atInfo().log("RPC受信結果：%s", request);
        return request;
    }

    public BondDataDto fallback(BondDataDto request, Exception e) {
        logger.atWarning().log("リクエストは実行されませんでした。対象リクエストID: %s [理由：%s]", request.getUuid(), e.getMessage());
        return request;
    }
}
