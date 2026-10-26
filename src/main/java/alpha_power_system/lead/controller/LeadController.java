package alpha_power_system.lead.controller;

import alpha_power_system.common.response.ApiResponse;
import alpha_power_system.lead.dto.request.CreateLeadRequest;
import alpha_power_system.lead.dto.request.UpdateLeadRequest;
import alpha_power_system.lead.dto.response.LeadResponse;
import alpha_power_system.lead.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public ApiResponse<LeadResponse> createLead(
            @Valid
            @RequestBody
            CreateLeadRequest request) {

        return ApiResponse.<LeadResponse>builder()
                .success(true)
                .message("Lead created successfully")
                .data(leadService.createLead(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<LeadResponse>>
    getAllLeads() {

        return ApiResponse
                .<List<LeadResponse>>builder()
                .success(true)
                .message("Leads fetched successfully")
                .data(leadService.getAllLeads())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<LeadResponse>
    getLeadById(@PathVariable UUID id) {

        return ApiResponse
                .<LeadResponse>builder()
                .success(true)
                .message("Lead fetched successfully")
                .data(leadService.getLeadById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<LeadResponse> updateLead(@PathVariable UUID id, @RequestBody UpdateLeadRequest request) {

        return ApiResponse
                .<LeadResponse>builder()
                .success(true)
                .message("Lead updated successfully")
                .data(leadService.updateLead(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteLead(@PathVariable UUID id) {

        leadService.deleteLead(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Lead deleted successfully")
                .data("Deleted")
                .build();
    }
}