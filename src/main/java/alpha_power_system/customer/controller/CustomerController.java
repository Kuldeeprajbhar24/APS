package alpha_power_system.customer.controller;

import alpha_power_system.common.response.ApiResponse;
import alpha_power_system.customer.dto.request.CreateCustomerRequest;
import alpha_power_system.customer.dto.request.UpdateCustomerRequest;
import alpha_power_system.customer.dto.response.CustomerResponse;
import alpha_power_system.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {

        return ApiResponse.<CustomerResponse>builder()
                .success(true)
                .message("Customer created successfully")
                .data(customerService.createCustomer(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CustomerResponse>> getAllCustomers() {

        return ApiResponse.<List<CustomerResponse>>builder()
                .success(true)
                .message("Customers fetched successfully")
                .data(customerService.getAllCustomers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponse> getCustomerById(
            @PathVariable UUID id) {

        return ApiResponse.<CustomerResponse>builder()
                .success(true)
                .message("Customer fetched successfully")
                .data(customerService.getCustomerById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CustomerResponse> updateCustomer(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCustomerRequest request) {

        return ApiResponse.<CustomerResponse>builder()
                .success(true)
                .message("Customer updated successfully")
                .data(customerService.updateCustomer(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCustomer(
            @PathVariable UUID id) {

        customerService.deleteCustomer(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Customer deleted successfully")
                .data("Deleted")
                .build();
    }
}