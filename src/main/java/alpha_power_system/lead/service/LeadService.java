package alpha_power_system.lead.service;

import alpha_power_system.lead.dto.request.CreateLeadRequest;
import alpha_power_system.lead.dto.request.UpdateLeadRequest;
import alpha_power_system.lead.dto.response.LeadResponse;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    LeadResponse createLead(CreateLeadRequest request);

    List<LeadResponse> getAllLeads();

    LeadResponse getLeadById(UUID id);

    LeadResponse updateLead(UUID id, UpdateLeadRequest request);

    void deleteLead(UUID id);
}