package com.example.dica.domain.usuario;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

    public UsuarioResponse getUsuarioById(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return new UsuarioResponse(usuario);
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

    public UsuarioResponse updateUsuario(UsuarioUpdateDto dto) {
        var usuario = usuarioRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (dto.email() != null && !dto.email().equals(usuario.getEmail())) {
            var existEmail = usuarioRepository.findByEmail(dto.email());
            if (existEmail != null) {
                throw new RuntimeException("Email já cadastrado");
            }
            usuario.setEmail(dto.email());
        }
        if (dto.nome() != null && !dto.nome().equals(usuario.getNome())) {
            usuario.setNome(dto.nome());
        }
        if (dto.role() != null && !dto.role().equals(usuario.getRole())) {
            usuario.setRole(dto.role());
        }

        return new UsuarioResponse(usuarioRepository.save(usuario));
    }

    public void deleteUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
}