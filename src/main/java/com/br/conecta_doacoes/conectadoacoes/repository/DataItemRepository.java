package com.br.conecta_doacoes.conectadoacoes.repository;

import com.br.conecta_doacoes.conectadoacoes.model.entity.DataItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataItemRepository extends JpaRepository<DataItem, Long> {
    Optional<DataItem> findByItemId(Long itemId);
    List<DataItem> findByItemUsuarioId(Long usuarioId);

}
