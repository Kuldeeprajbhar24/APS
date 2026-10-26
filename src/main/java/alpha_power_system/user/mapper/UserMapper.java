package alpha_power_system.user.mapper;


import alpha_power_system.user.dto.response.UserResponse;
import alpha_power_system.user.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }
}