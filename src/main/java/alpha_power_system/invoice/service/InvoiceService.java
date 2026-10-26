package alpha_power_system.invoice.service;

import alpha_power_system.invoice.dto.request.CreateInvoiceRequest;
import alpha_power_system.invoice.dto.request.UpdateInvoiceRequest;
import alpha_power_system.invoice.dto.response.InvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    InvoiceResponse createInvoice(CreateInvoiceRequest request);

    List<InvoiceResponse> getAllInvoices();

    InvoiceResponse getInvoiceById(UUID id);

    InvoiceResponse updateInvoice(UUID id, UpdateInvoiceRequest request);

    void deleteInvoice(UUID id);
}