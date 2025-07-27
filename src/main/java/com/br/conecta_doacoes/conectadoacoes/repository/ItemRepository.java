package com.br.conecta_doacoes.conectadoacoes.repository;

import com.br.conecta_doacoes.conectadoacoes.model.entity.Item;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Categoria;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Localizacao;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByTipo(Tipo tipo);

    // busca por uma única categoria
    List<Item> findByCategoria(Categoria categoria);

    // busca por uma única localização
    List<Item> findByLocalizacao(Localizacao localizacao);
}
