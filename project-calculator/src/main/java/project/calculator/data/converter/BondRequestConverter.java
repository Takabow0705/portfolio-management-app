package project.calculator.data.converter;

import io.grpc.finance.calculation.product.bond.BondPricingRequest;
import project.calculator.data.BondPricingData;

/**
 * gRPCで受信したDTOを計算用データクラスに変換するコンバータ
 */
public class BondRequestConverter {

   public static BondPricingData convertFrom(BondPricingRequest req){
        BondPricingData data = BondPricingData.builder()
                .bondCode(req.getBondCode())
                .bookPrice(BigDecimalConverter.convertFrom(req.getBookPrice()))
                .currentPrice(BigDecimalConverter.convertFrom(req.getCurrentPrice()))
                .couponRate(BigDecimalConverter.convertFrom(req.getCouponRate()))
                .currentMaturity(BigDecimalConverter.convertFrom(req.getCurrentMaturity()))
                .isTermEndPayment(req.getIsTermEndPayment())
                .paymentType(PaymetTypeConverter.convertFrom(req.getPaymentType()))
                .unit(BigDecimalConverter.convertFrom(req.getUnit()))
                .build();
       return data;
   }
}
