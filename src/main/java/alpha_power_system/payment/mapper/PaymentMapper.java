package alpha_power_system.payment.mapper;

import alpha_power_system.payment.dto.response.PaymentResponse;
import alpha_power_system.payment.entity.Payment;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentResponse toResponse(Payment payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentNumber(payment.getPaymentNumber())
                .invoiceId(payment.getInvoiceId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentDate(payment.getPaymentDate())
                .transactionReference(payment.getTransactionReference())
                .remarks(payment.getRemarks())
                .build();
    }
}