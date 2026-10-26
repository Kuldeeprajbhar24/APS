package alpha_power_system.invoice.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateInvoiceRequest(

        @NotNull(message = "Customer ID is required")
        UUID customerId,

        UUID quotationId,

        @NotNull(message = "Amount is required")
        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Amount must be greater than 0"
        )
        BigDecimal amount,

        @NotNull(message = "GST amount is required")
        @DecimalMin(
                value = "0.0",
                message = "GST amount cannot be negative"
        )
        BigDecimal gstAmount,

        @DecimalMin(
                value = "0.0",
                message = "Discount cannot be negative"
        )
        BigDecimal discount,

        @NotNull(message = "Invoice date is required")
        LocalDate invoiceDate,

        LocalDate dueDate,

        @Size(
                max = 1000,
                message = "Remarks cannot exceed 1000 characters"
        )
        String remarks
) {
}