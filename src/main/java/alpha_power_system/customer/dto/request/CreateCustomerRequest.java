package alpha_power_system.customer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCustomerRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String mobile;

    @Email
    private String email;

    private String companyName;

    private String gstNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;
}