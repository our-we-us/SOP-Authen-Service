package com.sop.chapter10.authservice.services;

import com.sop.chapter10.authservice.entities.User;
import com.sop.chapter10.authservice.entities.AuthRequest;
import com.sop.chapter10.authservice.entities.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwt;

    @Autowired
    public AuthService(final JwtUtil jwt) {this.jwt = jwt;}

    public AuthResponse logIn(AuthRequest authRequest) {
        User user = User.builder()
                .id("1")
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .role("admin").build();
        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }

}
