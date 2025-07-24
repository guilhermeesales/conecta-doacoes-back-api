package com.br.conecta_doacoes.conectadoacoes.repository;

import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Boolean existsByEmail(String email);
}
