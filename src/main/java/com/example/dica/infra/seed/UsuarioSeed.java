package com.example.dica.infra.seed;

import com.example.dica.domain.usuario.Role;
import com.example.dica.domain.usuario.Usuario;
import com.example.dica.domain.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.jspecify.annotations.NonNull;

@Component
public class UsuarioSeed implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioSeed(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(@NonNull String... args) {
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