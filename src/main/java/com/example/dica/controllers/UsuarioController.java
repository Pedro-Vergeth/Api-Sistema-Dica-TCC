package com.example.dica.controllers;

import com.example.dica.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/{id}")
    public ResponseEntity getUsuarioById(@PathVariable Long id) {
        var user = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @PostMapping
    public ResponseEntity createUsuario(@RequestBody @Valid UsuarioRequestDto request) {
        System.out.println(request);
        var userCreate = usuarioService.createUsuario(request);
        return ResponseEntity.ok(new UsuarioResponse(userCreate));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @PutMapping
    public ResponseEntity updateUsuario(@RequestBody @Valid UsuarioUpdateDto dto){
        System.out.println("Atualiza usuario");
        var userUpdated = usuarioService.updateUsuario(dto);
        return ResponseEntity.ok(userUpdated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/validar-senha")
    public ResponseEntity<ValidarSenhaResponseDto> validarSenha(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody @Valid ValidarSenhaRequestDto dto
    ) {
        return ResponseEntity.ok(usuarioService.validarSenha(usuario, dto));
    }
}
