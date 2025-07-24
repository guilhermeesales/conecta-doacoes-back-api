package com.br.conecta_doacoes.conectadoacoes.repository;

import com.br.conecta_doacoes.conectadoacoes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Boolean existsByUsername(String username);
}
