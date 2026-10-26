package alpha_power_system.user.dto.response;
import alpha_power_system.user.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {

    private UUID id;

    private String fullName;

    private String email;

    private String mobile;

    private Role role;

    private Boolean active;
}