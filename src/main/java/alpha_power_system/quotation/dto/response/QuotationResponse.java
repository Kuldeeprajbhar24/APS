package alpha_power_system.quotation.dto.response;

import alpha_power_system.quotation.entity.QuotationStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class QuotationResponse {

    private UUID id;

    private String quotationNumber;

    private UUID customerId;

    private String customerName;

    private UUID leadId;

    private String productInterest;

    private BigDecimal amount;

    private BigDecimal gstAmount;

    private BigDecimal discount;

    private BigDecimal finalAmount;

    private LocalDate validTill;

    private String remarks;

    private QuotationStatus status;
}