package alpha_power_system.invoice.mapper;

import alpha_power_system.invoice.dto.response.InvoiceResponse;
import alpha_power_system.invoice.entity.Invoice;

public class InvoiceMapper {

    private InvoiceMapper() {
    }

    public static InvoiceResponse toResponse(Invoice invoice) {

        return InvoiceResponse.builder()
                .id(invoice.getId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .customerId(invoice.getCustomerId())
                .quotationId(invoice.getQuotationId())
                .amount(invoice.getAmount())
                .gstAmount(invoice.getGstAmount())
                .discount(invoice.getDiscount())
                .totalAmount(invoice.getTotalAmount())
                .paidAmount(invoice.getPaidAmount())
                .dueAmount(invoice.getDueAmount())
                .invoiceDate(invoice.getInvoiceDate())
                .dueDate(invoice.getDueDate())
                .status(invoice.getStatus())
                .remarks(invoice.getRemarks())
                .build();
    }
}