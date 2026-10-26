package alpha_power_system.auth.service;

import alpha_power_system.auth.dto.AuthResponse;
import alpha_power_system.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}