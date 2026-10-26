package alpha_power_system.invoice.controller;

import alpha_power_system.common.response.ApiResponse;
import alpha_power_system.invoice.dto.request.CreateInvoiceRequest;
import alpha_power_system.invoice.dto.request.UpdateInvoiceRequest;
import alpha_power_system.invoice.dto.response.InvoiceResponse;
import alpha_power_system.invoice.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ApiResponse<InvoiceResponse> createInvoice(@Valid @RequestBody CreateInvoiceRequest request) {

        return ApiResponse.<InvoiceResponse>builder()
                .success(true)
                .message("Invoice created successfully")
                .data(invoiceService.createInvoice(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<InvoiceResponse>> getAllInvoices() {

        return ApiResponse.<List<InvoiceResponse>>builder()
                .success(true)
                .message("Invoices fetched successfully")
                .data(invoiceService.getAllInvoices())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<InvoiceResponse> getInvoiceById(@PathVariable UUID id) {

        return ApiResponse.<InvoiceResponse>builder()
                .success(true)
                .message("Invoice fetched successfully")
                .data(invoiceService.getInvoiceById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<InvoiceResponse> updateInvoice(@PathVariable UUID id, @Valid @RequestBody UpdateInvoiceRequest request) {

        return ApiResponse.<InvoiceResponse>builder()
                .success(true)
                .message("Invoice updated successfully")
                .data(invoiceService.updateInvoice(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteInvoice(@PathVariable UUID id) {

        invoiceService.deleteInvoice(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Invoice deleted successfully")
                .data("Invoice deleted successfully")
                .build();
    }
}