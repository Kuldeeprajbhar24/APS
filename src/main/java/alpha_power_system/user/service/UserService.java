package alpha_power_system.user.service;

import alpha_power_system.user.dto.request.CreateUserRequest;
import alpha_power_system.user.dto.response.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();
}