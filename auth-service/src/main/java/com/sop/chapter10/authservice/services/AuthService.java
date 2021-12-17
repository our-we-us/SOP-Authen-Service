package com.sop.chapter10.authservice.services;

import com.sop.chapter10.authservice.entities.User;
import com.sop.chapter10.authservice.entities.AuthRequest;
import com.sop.chapter10.authservice.entities.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {

    private final JwtUtil jwt;

    @Autowired
    public AuthService(final JwtUtil jwt) {this.jwt = jwt;}

    public AuthResponse logIn(AuthRequest authRequest) {
        Boolean isauth = WebClient.create()
                .get()
                .uri("http://localhost:9002/nurses/checklogin/"+authRequest.getUsername()+"/"+authRequest.getPassword())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (isauth){
            User user = User.builder()
                    .username(authRequest.getUsername())
                    .password(authRequest.getPassword())
                    .build();
            String accessToken = jwt.generate(user, "ACCESS");
            String refreshToken = jwt.generate(user, "REFRESH");
            return new AuthResponse(accessToken, refreshToken);
        }
        return null;

    }

}
