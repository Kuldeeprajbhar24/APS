package alpha_power_system.user.dto.request;

import alpha_power_system.user.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is required")
    private String fullName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}
