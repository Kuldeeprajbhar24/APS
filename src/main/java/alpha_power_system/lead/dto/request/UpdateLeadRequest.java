package alpha_power_system.lead.dto.request;

import alpha_power_system.lead.entity.LeadStatus;
import lombok.Data;

@Data
public class UpdateLeadRequest {

    private String productInterest;

    private String remarks;

    private LeadStatus status;
}