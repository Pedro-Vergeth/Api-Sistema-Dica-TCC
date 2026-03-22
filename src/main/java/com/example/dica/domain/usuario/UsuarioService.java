package com.example.dica.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsuarioResponse> getAllUsuario(Pageable pageable){
        Page<Usuario> usuario = usuarioRepository.findAll(pageable);
        Page<UsuarioResponse> usuarioResponse = usuario.map(UsuarioResponse::new);
        return usuarioResponse;
    }

    public Usuario createUsuario(UsuarioRequestDto dto) {
        System.out.println("Entrou em create " + dto);
        var exist = usuarioRepository.findByEmail(dto.email());
        System.out.println("valor de exist: " + exist);
        if(exist != null) {
            throw new RuntimeException("Email já cadastrado");
        }
        var passwordCrypto = passwordEncoder.encode(dto.password());
        return usuarioRepository.save(new Usuario(dto.nome(), dto.email(), passwordCrypto, dto.role()));
    }
}