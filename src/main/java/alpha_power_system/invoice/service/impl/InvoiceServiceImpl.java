package alpha_power_system.invoice.service.impl;

import alpha_power_system.exception.ResourceNotFoundException;
import alpha_power_system.invoice.dto.request.CreateInvoiceRequest;
import alpha_power_system.invoice.dto.request.UpdateInvoiceRequest;
import alpha_power_system.invoice.dto.response.InvoiceResponse;
import alpha_power_system.invoice.entity.Invoice;
import alpha_power_system.invoice.entity.InvoiceStatus;
import alpha_power_system.invoice.mapper.InvoiceMapper;
import alpha_power_system.invoice.repository.InvoiceRepository;
import alpha_power_system.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public InvoiceResponse createInvoice(
            CreateInvoiceRequest request) {

        BigDecimal gstAmount =
                request.gstAmount() != null ? request.gstAmount() : BigDecimal.ZERO;

        BigDecimal discount = request.discount() != null ? request.discount() : BigDecimal.ZERO;

        BigDecimal totalAmount = request.amount().add(gstAmount).subtract(discount);

        if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Total amount cannot be negative"
            );
        }

        BigDecimal paidAmount = BigDecimal.ZERO;

        BigDecimal dueAmount = totalAmount.subtract(paidAmount);

        String invoiceNumber = generateInvoiceNumber();

        Invoice invoice = Invoice.builder()
                .invoiceNumber(invoiceNumber)
                .customerId(request.customerId())
                .quotationId(request.quotationId())
                .amount(request.amount())
                .gstAmount(gstAmount)
                .discount(discount)
                .totalAmount(totalAmount)
                .paidAmount(paidAmount)
                .dueAmount(dueAmount)
                .invoiceDate(request.invoiceDate())
                .dueDate(request.dueDate())
                .status(InvoiceStatus.DRAFT)
                .remarks(request.remarks())
                .build();

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return InvoiceMapper.toResponse(savedInvoice);
    }

    @Override
    public List<InvoiceResponse> getAllInvoices() {

        return invoiceRepository.findAll()
                .stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    @Override
    public InvoiceResponse getInvoiceById(UUID id) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    public InvoiceResponse updateInvoice(
            UUID id,
            UpdateInvoiceRequest request) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Invoice not found"
                                )
                        );

        BigDecimal amount =
                request.amount() != null ? request.amount() : invoice.getAmount();

        BigDecimal gstAmount =
                request.gstAmount() != null ? request.gstAmount() : invoice.getGstAmount();

        BigDecimal discount =
                request.discount() != null ? request.discount() : invoice.getDiscount() != null ? invoice.getDiscount() : BigDecimal.ZERO;

        BigDecimal totalAmount = amount.add(gstAmount).subtract(discount);

        if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Total amount cannot be negative"
            );
        }

        BigDecimal paidAmount =
                invoice.getPaidAmount() != null ? invoice.getPaidAmount() : BigDecimal.ZERO;

        BigDecimal dueAmount = totalAmount.subtract(paidAmount);

        if (dueAmount.compareTo(BigDecimal.ZERO) < 0) {
            dueAmount = BigDecimal.ZERO;
        }

        invoice.setAmount(amount);
        invoice.setGstAmount(gstAmount);
        invoice.setDiscount(discount);
        invoice.setTotalAmount(totalAmount);
        invoice.setPaidAmount(paidAmount);
        invoice.setDueAmount(dueAmount);

        if (request.dueDate() != null) {
            invoice.setDueDate(request.dueDate());
        }

        if (request.status() != null) {
            invoice.setStatus(request.status());
        }

        if (request.remarks() != null) {
            invoice.setRemarks(request.remarks());
        }

        Invoice updatedInvoice =
                invoiceRepository.save(invoice);

        return InvoiceMapper.toResponse(updatedInvoice);
    }

    @Override
    public void deleteInvoice(UUID id) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

        invoiceRepository.delete(invoice);
    }

    private String generateInvoiceNumber() {

        return "APS-INV-" + Year.now().getValue() + "-" + System.currentTimeMillis();
    }
}