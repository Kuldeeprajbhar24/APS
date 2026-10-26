package alpha_power_system.payment.controller;

import alpha_power_system.common.response.ApiResponse;
import alpha_power_system.payment.dto.request.CreatePaymentRequest;
import alpha_power_system.payment.dto.request.UpdatePaymentRequest;
import alpha_power_system.payment.dto.response.PaymentResponse;
import alpha_power_system.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request) {

        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .message("Payment created successfully")
                .data(paymentService.createPayment(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getAllPayments() {

        return ApiResponse.<List<PaymentResponse>>builder()
                .success(true)
                .message("Payments fetched successfully")
                .data(paymentService.getAllPayments())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable UUID id) {

        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .message("Payment fetched successfully")
                .data(paymentService.getPaymentById(id))
                .build();
    }

    @GetMapping("/invoice/{invoiceId}")
    public ApiResponse<List<PaymentResponse>>
    getPaymentsByInvoiceId(
            @PathVariable UUID invoiceId) {

        return ApiResponse.<List<PaymentResponse>>builder()
                .success(true)
                .message("Invoice payments fetched successfully")
                .data(paymentService.getPaymentsByInvoiceId(invoiceId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PaymentResponse> updatePayment(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePaymentRequest request) {

        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .message("Payment updated successfully")
                .data(paymentService.updatePayment(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePayment(@PathVariable UUID id) {

        paymentService.deletePayment(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Payment deleted successfully")
                .data("Payment deleted successfully")
                .build();
    }
}