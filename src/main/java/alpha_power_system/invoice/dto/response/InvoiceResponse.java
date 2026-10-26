package alpha_power_system.invoice.dto.response;

import alpha_power_system.invoice.entity.InvoiceStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class InvoiceResponse {

    private UUID id;

    private String invoiceNumber;

    private UUID customerId;

    private UUID quotationId;

    private BigDecimal amount;

    private BigDecimal gstAmount;

    private BigDecimal discount;

    private BigDecimal totalAmount;

    private BigDecimal paidAmount;

    private BigDecimal dueAmount;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private InvoiceStatus status;

    private String remarks;
}