package alpha_power_system.quotation.service;

import alpha_power_system.quotation.dto.request.CreateQuotationRequest;
import alpha_power_system.quotation.dto.request.UpdateQuotationRequest;
import alpha_power_system.quotation.dto.response.QuotationResponse;

import java.util.List;
import java.util.UUID;

public interface QuotationService {

    QuotationResponse createQuotation(
            CreateQuotationRequest request);

    List<QuotationResponse> getAllQuotations();

    QuotationResponse getQuotationById(
            UUID id);

    QuotationResponse updateQuotation(
            UUID id,
            UpdateQuotationRequest request);

    void deleteQuotation(
            UUID id);
}