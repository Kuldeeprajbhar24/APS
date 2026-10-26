package alpha_power_system.payment.dto.request;

import alpha_power_system.payment.entity.PaymentMethod;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreatePaymentRequest(

        @NotNull(message = "Invoice ID is required")
        UUID invoiceId,

        @NotNull(message = "Payment amount is required")
        @DecimalMin(value = "0.01", message = "Payment amount must be greater than 0")
        BigDecimal amount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Payment date is required")
        LocalDate paymentDate,

        @Size(max = 100, message = "Transaction reference cannot exceed 100 characters")
        String transactionReference,

        @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
        String remarks
) {
}