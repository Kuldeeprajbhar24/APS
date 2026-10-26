package alpha_power_system.lead.service.impl;

import alpha_power_system.customer.entity.Customer;
import alpha_power_system.customer.repository.CustomerRepository;
import alpha_power_system.exception.ResourceNotFoundException;
import alpha_power_system.lead.dto.request.CreateLeadRequest;
import alpha_power_system.lead.dto.request.UpdateLeadRequest;
import alpha_power_system.lead.dto.response.LeadResponse;
import alpha_power_system.lead.entity.Lead;
import alpha_power_system.lead.entity.LeadStatus;
import alpha_power_system.lead.mapper.LeadMapper;
import alpha_power_system.lead.repository.LeadRepository;
import alpha_power_system.lead.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl
        implements LeadService {

    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;

    @Override
    public LeadResponse createLead(
            CreateLeadRequest request) {

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Lead lead = Lead.builder()
                .customer(customer)
                .productInterest(request.getProductInterest())
                .remarks(request.getRemarks())
                .status(LeadStatus.NEW)
                .build();

        return LeadMapper.toResponse(
                leadRepository.save(lead));
    }

    @Override
    public List<LeadResponse> getAllLeads() {

        return leadRepository.findAll()
                .stream()
                .map(LeadMapper::toResponse)
                .toList();
    }

    @Override
    public LeadResponse getLeadById(
            UUID id) {

        Lead lead =
                leadRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        return LeadMapper.toResponse(
                lead);
    }

    @Override
    public LeadResponse updateLead(
            UUID id,
            UpdateLeadRequest request) {

        Lead lead =
                leadRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        lead.setProductInterest(request.getProductInterest());
        lead.setRemarks(request.getRemarks());
        lead.setStatus(request.getStatus());
        return LeadMapper.toResponse(
                leadRepository.save(lead));
    }

    @Override
    public void deleteLead(
            UUID id) {

        Lead lead =
                leadRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        leadRepository.delete(lead);
    }
}