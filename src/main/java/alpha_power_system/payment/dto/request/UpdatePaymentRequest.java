package alpha_power_system.payment.dto.request;

import alpha_power_system.payment.entity.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdatePaymentRequest(

        @DecimalMin(value = "0.01", message = "Payment amount must be greater than 0")
        BigDecimal amount,

        PaymentMethod paymentMethod,

        LocalDate paymentDate,

        @Size(max = 100, message = "Transaction reference cannot exceed 100 characters")
        String transactionReference,

        @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
        String remarks
) {
}