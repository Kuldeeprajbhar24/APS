package alpha_power_system.customer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCustomerRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Mobile is required")
    private String mobile;

    @Email(message = "Invalid email format")
    private String email;

    private String companyName;

    private String gstNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private Boolean active;
}