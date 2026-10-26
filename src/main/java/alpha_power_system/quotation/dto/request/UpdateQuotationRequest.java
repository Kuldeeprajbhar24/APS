package alpha_power_system.quotation.dto.request;

import alpha_power_system.quotation.entity.QuotationStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateQuotationRequest {

    private BigDecimal amount;

    private BigDecimal gstAmount;

    private BigDecimal discount;

    private LocalDate validTill;

    private String remarks;

    private QuotationStatus status;
}