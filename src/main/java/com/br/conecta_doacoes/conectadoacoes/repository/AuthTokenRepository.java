package com.br.conecta_doacoes.conectadoacoes.repository;

import com.br.conecta_doacoes.conectadoacoes.model.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findByToken(String token);
    void deleteByToken(String token);
    void deleteByEmail(String email);

}
