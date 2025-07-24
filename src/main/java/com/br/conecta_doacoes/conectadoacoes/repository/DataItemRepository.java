package com.br.conecta_doacoes.conectadoacoes.repository;

import com.br.conecta_doacoes.conectadoacoes.model.entity.DataItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemRepository extends JpaRepository<DataItem, Long> {
}
