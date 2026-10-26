package alpha_power_system.payment.service.impl;

import alpha_power_system.exception.ResourceNotFoundException;
import alpha_power_system.invoice.entity.Invoice;
import alpha_power_system.invoice.entity.InvoiceStatus;
import alpha_power_system.invoice.repository.InvoiceRepository;
import alpha_power_system.payment.dto.request.CreatePaymentRequest;
import alpha_power_system.payment.dto.request.UpdatePaymentRequest;
import alpha_power_system.payment.dto.response.PaymentResponse;
import alpha_power_system.payment.entity.Payment;
import alpha_power_system.payment.mapper.PaymentMapper;
import alpha_power_system.payment.repository.PaymentRepository;
import alpha_power_system.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final InvoiceRepository invoiceRepository;

    @Override
    public PaymentResponse createPayment(
            CreatePaymentRequest request) {

        Invoice invoice =
                invoiceRepository.findById(request.invoiceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Invoice not found")
                        );

        BigDecimal paymentAmount = request.amount();

        BigDecimal currentPaidAmount =
                invoice.getPaidAmount() != null ? invoice.getPaidAmount() : BigDecimal.ZERO;

        BigDecimal totalAmount =
                invoice.getTotalAmount();

        BigDecimal currentDueAmount =
                totalAmount.subtract(currentPaidAmount);

        if (paymentAmount.compareTo(currentDueAmount) > 0) {
            throw new IllegalArgumentException(
                    "Payment amount cannot be greater than due amount"
            );
        }

        BigDecimal newPaidAmount = currentPaidAmount.add(paymentAmount);

        BigDecimal newDueAmount = totalAmount.subtract(newPaidAmount);

        invoice.setPaidAmount(newPaidAmount);
        invoice.setDueAmount(newDueAmount);

        if (newDueAmount.compareTo(BigDecimal.ZERO) == 0) {

            invoice.setStatus(InvoiceStatus.PAID);

        } else {

            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        }

        invoiceRepository.save(invoice);

        String paymentNumber = generatePaymentNumber();

        Payment payment = Payment.builder()
                .paymentNumber(paymentNumber)
                .invoiceId(request.invoiceId())
                .amount(paymentAmount)
                .paymentMethod(request.paymentMethod())
                .paymentDate(request.paymentDate())
                .transactionReference(request.transactionReference())
                .remarks(request.remarks())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentMapper.toResponse(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByInvoiceId(
            UUID invoiceId) {

        if (!invoiceRepository.existsById(invoiceId)) {

            throw new ResourceNotFoundException("Invoice not found");
        }

        return paymentRepository
                .findByInvoiceId(invoiceId)
                .stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(UUID id) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Payment not found")
                        );

        return PaymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse updatePayment(
            UUID id,
            UpdatePaymentRequest request) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Payment not found")
                        );

        /*
         * Payment amount update requires recalculating
         * the related invoice.
         */

        if (request.amount() != null &&
                request.amount().compareTo(payment.getAmount()) != 0) {

            updateInvoicePaymentAmount(payment, request.amount());

            payment.setAmount(request.amount());
        }

        if (request.paymentMethod() != null) {

            payment.setPaymentMethod(request.paymentMethod());
        }

        if (request.paymentDate() != null) {

            payment.setPaymentDate(request.paymentDate());
        }

        if (request.transactionReference() != null) {

            payment.setTransactionReference(request.transactionReference());
        }

        if (request.remarks() != null) {

            payment.setRemarks(request.remarks());
        }

        Payment updatedPayment = paymentRepository.save(payment);

        return PaymentMapper.toResponse(updatedPayment);
    }

    @Override
    public void deletePayment(UUID id) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Payment not found"));

        /*
         * Before deleting payment, reverse its
         * effect on the invoice.
         */

        Invoice invoice =
                invoiceRepository.findById(payment.getInvoiceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Invoice not found"));

        BigDecimal currentPaidAmount =
                invoice.getPaidAmount() != null ? invoice.getPaidAmount() : BigDecimal.ZERO;

        BigDecimal newPaidAmount = currentPaidAmount.subtract(payment.getAmount());

        if (newPaidAmount.compareTo(BigDecimal.ZERO) < 0) {
            newPaidAmount = BigDecimal.ZERO;
        }

        BigDecimal newDueAmount =
                invoice.getTotalAmount().subtract(newPaidAmount);

        invoice.setPaidAmount(newPaidAmount);
        invoice.setDueAmount(newDueAmount);

        if (newPaidAmount.compareTo(BigDecimal.ZERO) == 0) {

            invoice.setStatus(InvoiceStatus.ISSUED);

        } else if (newDueAmount.compareTo(BigDecimal.ZERO) == 0) {

            invoice.setStatus(InvoiceStatus.PAID);

        } else {

            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        }

        invoiceRepository.save(invoice);

        paymentRepository.delete(payment);
    }

    private void updateInvoicePaymentAmount(Payment payment, BigDecimal newPaymentAmount) {

        Invoice invoice = invoiceRepository.findById(payment.getInvoiceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Invoice not found"));

        BigDecimal currentPaidAmount = invoice.getPaidAmount() != null ? invoice.getPaidAmount() : BigDecimal.ZERO;

        /*
         * Remove old payment amount and add
         * the new payment amount.
         */

        BigDecimal paidWithoutCurrentPayment = currentPaidAmount.subtract(payment.getAmount());

        if (paidWithoutCurrentPayment.compareTo(BigDecimal.ZERO) < 0) {

            paidWithoutCurrentPayment = BigDecimal.ZERO;
        }

        BigDecimal newPaidAmount = paidWithoutCurrentPayment.add(newPaymentAmount);

        BigDecimal totalAmount = invoice.getTotalAmount();

        if (newPaidAmount.compareTo(totalAmount) > 0) {

            throw new IllegalArgumentException("Payment amount cannot be greater than invoice due amount");
        }

        BigDecimal newDueAmount = totalAmount.subtract(newPaidAmount);
        invoice.setPaidAmount(newPaidAmount);
        invoice.setDueAmount(newDueAmount);

        if (newDueAmount.compareTo(BigDecimal.ZERO) == 0) {

            invoice.setStatus(InvoiceStatus.PAID);

        } else {

            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        }

        invoiceRepository.save(invoice);
    }

    private String generatePaymentNumber() {

        return "APS-PAY-" + Year.now().getValue() + "-" + System.currentTimeMillis();
    }
}