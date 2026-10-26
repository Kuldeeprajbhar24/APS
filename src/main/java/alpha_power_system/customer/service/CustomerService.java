package alpha_power_system.customer.service;

import alpha_power_system.customer.dto.request.CreateCustomerRequest;
import alpha_power_system.customer.dto.request.UpdateCustomerRequest;
import alpha_power_system.customer.dto.response.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(
            CreateCustomerRequest request);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(
            UUID id);

    CustomerResponse updateCustomer(
            UUID id,
            UpdateCustomerRequest request);

    void deleteCustomer(
            UUID id);
}