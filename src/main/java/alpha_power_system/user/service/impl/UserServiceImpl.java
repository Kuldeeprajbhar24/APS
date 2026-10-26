package alpha_power_system.user.service.impl;


import alpha_power_system.exception.DuplicateResourceException;
import alpha_power_system.user.dto.request.CreateUserRequest;
import alpha_power_system.user.dto.response.UserResponse;
import alpha_power_system.user.entity.User;
import alpha_power_system.user.mapper.UserMapper;
import alpha_power_system.user.repository.UserRepository;
import alpha_power_system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        return UserMapper.toResponse(
                userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }
}
