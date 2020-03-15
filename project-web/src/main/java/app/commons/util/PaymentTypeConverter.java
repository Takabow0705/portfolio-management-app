package app.commons.util;

import io.grpc.finance.calculation.product.bond.PaymentTypeRpc;

public class PaymentTypeConverter {

    public static PaymentTypeRpc covertFrom(String paymentType){
        switch (paymentType){
            case "SemiAnnual":
                return  PaymentTypeRpc.SEMI_ANNUAL;
            case "Annual":
            default:
                return PaymentTypeRpc.ANNUAL;
        }
    }
}
