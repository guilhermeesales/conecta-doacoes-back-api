package com.br.conecta_doacoes.conectadoacoes.filter;

import com.br.conecta_doacoes.conectadoacoes.service.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final AuthService authService;

    private static final Set<String> PUBLIC_PATH_PREFIXES = Set.of(
            "/api/auth/login",
            "/api/usuarios/cadastrar",
            "/api/itens/obter-todos",
            "/swagger-ui",
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources",
            "/webjars/",
            "/swagger-ui.html"
    );

    public AuthTokenFilter(@Lazy AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("X-Auth-Token");

        if (token == null || !authService.isTokenValid(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        authService.getEmailFromToken(token).ifPresent(email -> {
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATH_PREFIXES.stream().anyMatch(path::startsWith);
    }
}
