package alpha_power_system.customer.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerResponse {

    private UUID id;

    private String customerName;

    private String mobile;

    private String email;

    private String companyName;

    private String gstNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private Boolean active;
}