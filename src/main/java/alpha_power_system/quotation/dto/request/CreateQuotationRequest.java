package alpha_power_system.quotation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateQuotationRequest {

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID leadId;

    @NotNull
    private BigDecimal amount;

    private BigDecimal gstAmount;

    private BigDecimal discount;

    private LocalDate validTill;

    private String remarks;
}