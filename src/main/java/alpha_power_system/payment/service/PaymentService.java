package alpha_power_system.payment.service;

import alpha_power_system.payment.dto.request.CreatePaymentRequest;
import alpha_power_system.payment.dto.request.UpdatePaymentRequest;
import alpha_power_system.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentResponse createPayment(CreatePaymentRequest request);

    List<PaymentResponse> getAllPayments();

    List<PaymentResponse> getPaymentsByInvoiceId(UUID invoiceId);

    PaymentResponse getPaymentById(UUID id);

    PaymentResponse updatePayment(UUID id, UpdatePaymentRequest request);

    void deletePayment(UUID id);
}