package alpha_power_system.user.controller;


import alpha_power_system.common.entity.ApiResponse;
import alpha_power_system.user.dto.request.CreateUserRequest;
import alpha_power_system.user.dto.response.UserResponse;
import alpha_power_system.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(
            @Valid @RequestBody
            CreateUserRequest request) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {

        return ApiResponse
                .<List<UserResponse>>builder()
                .success(true)
                .message("Users fetched successfully")
                .data(userService.getAllUsers())
                .build();
    }
}