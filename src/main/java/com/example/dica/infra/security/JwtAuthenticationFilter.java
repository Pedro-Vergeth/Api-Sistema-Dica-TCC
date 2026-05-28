package com.example.dica.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. ATALHO PARA O CORS: Se for OPTIONS, ignora a verificação de token e segue a vida
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        // 2. TRY-CATCH APENAS NA LÓGICA DO TOKEN
        try {
            if (header != null && header.startsWith("Bearer ")) {
                var token = header.replace("Bearer ", "").trim();
                var email = tokenService.getSubject(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var userDetails = userDetailsService.loadUserByUsername(email);
                    var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            // Se o token for inválido, expirado, etc., não fazemos nada aqui.
            // O contexto do Spring Security vai ficar nulo.
            // O próprio Spring Security vai bloquear a requisição e retornar 403/401 automaticamente!
            System.out.println("Erro ao validar token: " + e.getMessage()); // Opcional, só para você ver no terminal
        }

        // 3. doFilter FORA DO TRY-CATCH (Isso é obrigatório no Spring)
        filterChain.doFilter(request, response);
    }
}