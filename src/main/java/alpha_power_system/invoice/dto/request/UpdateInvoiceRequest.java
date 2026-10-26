package alpha_power_system.invoice.dto.request;

import alpha_power_system.invoice.entity.InvoiceStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateInvoiceRequest(

        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Amount must be greater than 0"
        )
        BigDecimal amount,

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

        LocalDate dueDate,

        InvoiceStatus status,

        @Size(
                max = 1000,
                message = "Remarks cannot exceed 1000 characters"
        )
        String remarks
) {
}