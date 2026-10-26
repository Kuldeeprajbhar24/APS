package alpha_power_system.auth.service.impl;

import alpha_power_system.auth.dto.AuthResponse;
import alpha_power_system.auth.dto.LoginRequest;
import alpha_power_system.auth.service.AuthService;
import alpha_power_system.exception.BadRequestException;
import alpha_power_system.security.JwtService;
import alpha_power_system.user.entity.User;
import alpha_power_system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadRequestException(
                                "Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BadRequestException(
                    "Invalid email or password");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}