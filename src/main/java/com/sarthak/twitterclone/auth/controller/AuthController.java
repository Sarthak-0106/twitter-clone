package com.sarthak.twitterclone.auth.controller;

import com.sarthak.twitterclone.auth.dto.LoginRequest;
import com.sarthak.twitterclone.auth.dto.RegisterRequest;
import com.sarthak.twitterclone.auth.security.JwtUtil;
import com.sarthak.twitterclone.auth.service.AuthService;
import com.sarthak.twitterclone.user.User;
import com.sarthak.twitterclone.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthService authService, JwtUtil jwtUtil){
        this.userService = userService;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest registerRequest){
        userService.registerUser(
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        User user = authService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getRole().name());
        return ResponseEntity.ok(Map.of("accessToken", accessToken));
    }
}
