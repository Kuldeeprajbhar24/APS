package alpha_power_system.customer.service.impl;

import alpha_power_system.customer.dto.request.CreateCustomerRequest;
import alpha_power_system.customer.dto.request.UpdateCustomerRequest;
import alpha_power_system.customer.dto.response.CustomerResponse;
import alpha_power_system.customer.entity.Customer;
import alpha_power_system.customer.mapper.CustomerMapper;
import alpha_power_system.customer.repository.CustomerRepository;
import alpha_power_system.customer.service.CustomerService;
import alpha_power_system.exception.DuplicateResourceException;
import alpha_power_system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl
        implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(
            CreateCustomerRequest request) {

        if (customerRepository.existsByMobile(
                request.getMobile())) {

            throw new DuplicateResourceException(
                    "Mobile already exists");
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && customerRepository.existsByEmail(
                request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists");
        }

        Customer customer = Customer.builder()
                .customerName(
                        request.getCustomerName())
                .mobile(
                        request.getMobile())
                .email(
                        request.getEmail())
                .companyName(
                        request.getCompanyName())
                .gstNumber(
                        request.getGstNumber())
                .address(
                        request.getAddress())
                .city(
                        request.getCity())
                .state(
                        request.getState())
                .pincode(
                        request.getPincode())
                .active(true)
                .build();

        return CustomerMapper.toResponse(
                customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(
            UUID id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found"));

        return CustomerMapper.toResponse(
                customer);
    }

    @Override
    public CustomerResponse updateCustomer(
            UUID id,
            UpdateCustomerRequest request) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found"));

        customer.setCustomerName(
                request.getCustomerName());
        customer.setMobile(
                request.getMobile());
        customer.setEmail(
                request.getEmail());
        customer.setCompanyName(
                request.getCompanyName());
        customer.setGstNumber(
                request.getGstNumber());
        customer.setAddress(
                request.getAddress());
        customer.setCity(
                request.getCity());
        customer.setState(
                request.getState());
        customer.setPincode(
                request.getPincode());
        customer.setActive(
                request.getActive());

        return CustomerMapper.toResponse(
                customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(
            UUID id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found"));

        customerRepository.delete(customer);
    }
}