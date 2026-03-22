package com.example.dica.domain.usuario;

public record UsuarioResponse(Long id, String nome, String email, Role role) {

    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }

}
