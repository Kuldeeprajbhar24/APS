package alpha_power_system.lead.dto.response;

import alpha_power_system.lead.entity.LeadStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LeadResponse {

    private UUID id;

    private UUID customerId;

    private String customerName;

    private String productInterest;

    private String remarks;

    private LeadStatus status;
}