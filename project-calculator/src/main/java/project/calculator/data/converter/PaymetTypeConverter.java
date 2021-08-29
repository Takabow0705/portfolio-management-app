package project.calculator.data.converter;

import io.grpc.finance.calculation.product.bond.PaymentTypeRpc;
import project.infra.rdb.discountfactor.PaymentType;

/**
 * gRPCで定義されたPaymentTypeをこちら側のPaymentType型に変換する。
 */
public class PaymetTypeConverter {

    public static PaymentType convertFrom(PaymentTypeRpc paymentTypeRpc){
        if (paymentTypeRpc == PaymentTypeRpc.ANNUAL){
            return PaymentType.Annual;
        }

        if (paymentTypeRpc == PaymentTypeRpc.SEMI_ANNUAL){
            return PaymentType.SemiAnnual;
        }

        return null;
    }

}
