package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.model.entity.AuthToken;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.br.conecta_doacoes.conectadoacoes.repository.AuthTokenRepository;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthTokenRepository authTokenRepository;

    public AuthService(UsuarioRepository usuarioRepository,
                       AuthTokenRepository authTokenRepository) {
        this.usuarioRepository = usuarioRepository;
        this.authTokenRepository = authTokenRepository;
    }

    public Optional<String> authenticate(String email, String password, PasswordEncoder passwordEncoder) {
        Optional<Usuario> optionalUser = Optional.ofNullable(usuarioRepository.findByEmail(email));

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = UUID.randomUUID().toString();
                AuthToken authToken = new AuthToken(token, email);
                authTokenRepository.save(authToken);
                return Optional.of(token);
            }
        }

        return Optional.empty();
    }

    public boolean isTokenValid(String token) {
        return authTokenRepository.findByToken(token).isPresent();
    }

    public Optional<String> getEmailFromToken(String token) {
        return authTokenRepository.findByToken(token)
                .map(AuthToken::getEmail);
    }
}
