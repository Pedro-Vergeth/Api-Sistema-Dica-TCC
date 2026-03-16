package com.example.dica.controllers;

import com.example.dica.domain.usuario.LoginRequestDto;
import com.example.dica.domain.usuario.Usuario;
import com.example.dica.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;

@RestController
@RequestMapping("/gerenciador/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody @Valid LoginRequestDto loginRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        try {
            Authentication authentication = manager.authenticate(authToken);
            var tokenJwt = tokenService.generateToken(authentication);
            return ResponseEntity.ok(tokenJwt);
        }
        catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }
}
