package alpha_power_system.lead.mapper;

import alpha_power_system.lead.dto.response.LeadResponse;
import alpha_power_system.lead.entity.Lead;

public class LeadMapper {

    private LeadMapper() {
    }

    public static LeadResponse toResponse(Lead lead) {

        return LeadResponse.builder()
                .id(lead.getId())
                .customerId(lead.getCustomer().getId())
                .customerName(lead.getCustomer().getCustomerName())
                .productInterest(lead.getProductInterest())
                .remarks(lead.getRemarks())
                .status(lead.getStatus())
                .build();
    }
}