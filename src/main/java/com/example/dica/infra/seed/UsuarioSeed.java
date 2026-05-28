package com.example.dica.infra.seed;

import com.example.dica.domain.usuario.Role;
import com.example.dica.domain.usuario.Usuario;
import com.example.dica.domain.usuario.UsuarioRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsuarioSeed {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioSeed(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void seedUsuarioAdmin() {
        String nome = "Administrador";
        String email = "admin@dica.com";
        String senhaPadrao = "admin123";

        if (usuarioRepository.findByEmail(email) != null) {
            return;
        }

        String senhaCriptografada = passwordEncoder.encode(senhaPadrao);
        Usuario usuario = new Usuario(nome, email, senhaCriptografada, Role.ADMIN);
        usuarioRepository.save(usuario);

    }
}