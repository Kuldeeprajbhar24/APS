package alpha_power_system.quotation.service.impl;

import alpha_power_system.customer.entity.Customer;
import alpha_power_system.customer.repository.CustomerRepository;
import alpha_power_system.exception.ResourceNotFoundException;
import alpha_power_system.lead.entity.Lead;
import alpha_power_system.lead.repository.LeadRepository;
import alpha_power_system.quotation.dto.request.CreateQuotationRequest;
import alpha_power_system.quotation.dto.request.UpdateQuotationRequest;
import alpha_power_system.quotation.dto.response.QuotationResponse;
import alpha_power_system.quotation.entity.Quotation;
import alpha_power_system.quotation.entity.QuotationStatus;
import alpha_power_system.quotation.mapper.QuotationMapper;
import alpha_power_system.quotation.repository.QuotationRepository;
import alpha_power_system.quotation.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuotationServiceImpl
        implements QuotationService {

    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final LeadRepository leadRepository;

    @Override
    public QuotationResponse createQuotation(
            CreateQuotationRequest request) {

        Customer customer =
                customerRepository.findById(
                                request.getCustomerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Lead lead =
                leadRepository.findById(
                                request.getLeadId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Lead not found"));

        BigDecimal gst = request.getGstAmount() == null
                        ? BigDecimal.ZERO
                        : request.getGstAmount();

        BigDecimal discount = request.getDiscount() == null
                        ? BigDecimal.ZERO
                        : request.getDiscount();

        BigDecimal finalAmount = request.getAmount()
                        .add(gst)
                        .subtract(discount);

        String quotationNumber = "APS-Q-" + Year.now().getValue() + "-" + System.currentTimeMillis();

        Quotation quotation =
                Quotation.builder()
                        .quotationNumber(quotationNumber)
                        .customer(customer)
                        .lead(lead)
                        .amount(request.getAmount())
                        .gstAmount(gst)
                        .discount(discount)
                        .finalAmount(finalAmount)
                        .validTill(request.getValidTill())
                        .remarks(request.getRemarks())
                        .status(QuotationStatus.DRAFT)
                        .build();

        return QuotationMapper.toResponse(
                quotationRepository.save(
                        quotation));
    }

    @Override
    public List<QuotationResponse>
    getAllQuotations() {

        return quotationRepository.findAll()
                .stream()
                .map(QuotationMapper::toResponse)
                .toList();
    }

    @Override
    public QuotationResponse getQuotationById(UUID id) {

        Quotation quotation = quotationRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));

        return QuotationMapper.toResponse(quotation);
    }

    @Override
    public QuotationResponse updateQuotation(UUID id, UpdateQuotationRequest request) {

        Quotation quotation =
                quotationRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));

        quotation.setAmount(request.getAmount());
        quotation.setGstAmount(request.getGstAmount());
        quotation.setDiscount(request.getDiscount());
        quotation.setValidTill(request.getValidTill());
        quotation.setRemarks(request.getRemarks());
        quotation.setStatus(request.getStatus());

        quotation.setFinalAmount(
                request.getAmount()
                        .add(request.getGstAmount())
                        .subtract(request.getDiscount())
        );

        return QuotationMapper.toResponse(quotationRepository.save(quotation));
    }

    @Override
    public void deleteQuotation(UUID id) {

        Quotation quotation = quotationRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));

        quotationRepository.delete(quotation);
    }
}