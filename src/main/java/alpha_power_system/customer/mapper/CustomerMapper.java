package alpha_power_system.customer.mapper;

import alpha_power_system.customer.dto.response.CustomerResponse;
import alpha_power_system.customer.entity.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerResponse toResponse(
            Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .mobile(customer.getMobile())
                .email(customer.getEmail())
                .companyName(customer.getCompanyName())
                .gstNumber(customer.getGstNumber())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .pincode(customer.getPincode())
                .active(customer.getActive())
                .build();
    }
}