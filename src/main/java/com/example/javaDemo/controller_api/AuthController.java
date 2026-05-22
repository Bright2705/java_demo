package com.example.javaDemo.controller_api;

import com.example.javaDemo.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.javaDemo.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    public AuthController(AuthenticationManager authManager,JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        return jwtService.generateToken(request.getUsername());
    }

    @PostMapping("/refresh")
    public String refresh(@RequestBody String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);

        return jwtService.generateToken(username);
    }
}
