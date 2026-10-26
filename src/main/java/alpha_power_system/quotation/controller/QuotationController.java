package alpha_power_system.quotation.controller;

import alpha_power_system.common.response.ApiResponse;
import alpha_power_system.quotation.dto.request.CreateQuotationRequest;
import alpha_power_system.quotation.dto.request.UpdateQuotationRequest;
import alpha_power_system.quotation.dto.response.QuotationResponse;
import alpha_power_system.quotation.service.QuotationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quotations")
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;

    @PostMapping
    public ApiResponse<QuotationResponse>
    createQuotation(@Valid @RequestBody CreateQuotationRequest request) {

        return ApiResponse
                .<QuotationResponse>builder()
                .success(true)
                .message("Quotation created successfully")
                .data(quotationService.createQuotation(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<QuotationResponse>> getAllQuotations() {

        return ApiResponse
                .<List<QuotationResponse>>builder()
                .success(true)
                .message("Quotations fetched successfully")
                .data(quotationService.getAllQuotations())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<QuotationResponse>
    getQuotationById(@PathVariable UUID id) {

        return ApiResponse
                .<QuotationResponse>builder()
                .success(true)
                .message("Quotation fetched successfully")
                .data(quotationService.getQuotationById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<QuotationResponse>
    updateQuotation(@PathVariable UUID id, @RequestBody UpdateQuotationRequest request) {

        return ApiResponse
                .<QuotationResponse>builder()
                .success(true)
                .message("Quotation updated successfully")
                .data(quotationService.updateQuotation(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String>
    deleteQuotation(@PathVariable UUID id) {

        quotationService.deleteQuotation(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Quotation deleted successfully")
                .data("Deleted")
                .build();
    }
}