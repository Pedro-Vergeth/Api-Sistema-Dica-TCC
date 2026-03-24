package com.example.dica.controllers;

import com.example.dica.domain.usuario.JwtResponseDto;
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
@RequestMapping("api/gerenciador/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody @Valid LoginRequestDto loginRequest) {
        System.out.println("senha " + passwordEncoder.encode(loginRequest.password()));
        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        var authentication = manager.authenticate(authToken);
        var tokenJwt = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponseDto(tokenJwt));
    }

    @PostMapping("forgot_password")
    public ResponseEntity forgotPassword(@RequestBody String email) {
        return ResponseEntity.ok("Email de recuperação enviado para o time de desenvolvimento: " + email);
    }

    @PostMapping("create_user")
    public ResponseEntity createUser(@RequestBody String email){
        return ResponseEntity.ok("Solicitação de criação de usuário enviada ao time de desenvolvedores: " + email);
    }

}
