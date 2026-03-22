package com.example.dica.controllers;

import com.example.dica.domain.usuario.Usuario;
import com.example.dica.domain.usuario.UsuarioRequestDto;
import com.example.dica.domain.usuario.UsuarioResponse;
import com.example.dica.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gerenciador/usuario")
@EnableMethodSecurity
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> getUsuario(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getAllUsuario(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody @Valid UsuarioRequestDto request) {
        System.out.println(request);
        var userCreate = usuarioService.createUsuario(request);
        return ResponseEntity.ok(new UsuarioResponse(userCreate));

    }
}
