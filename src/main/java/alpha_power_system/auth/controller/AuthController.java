package alpha_power_system.auth.controller;

import alpha_power_system.auth.dto.AuthResponse;
import alpha_power_system.auth.dto.LoginRequest;
import alpha_power_system.auth.service.AuthService;
import alpha_power_system.common.entity.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid
            @RequestBody
            LoginRequest request) {

        return ApiResponse
                .<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(authService.login(request))
                .build();
    }
}