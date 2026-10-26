package alpha_power_system.lead.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateLeadRequest {

    @NotNull
    private UUID customerId;

    @NotBlank
    private String productInterest;

    private String remarks;
}