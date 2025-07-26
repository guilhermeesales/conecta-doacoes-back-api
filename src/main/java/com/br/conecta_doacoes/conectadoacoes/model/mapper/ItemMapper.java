package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.br.conecta_doacoes.conectadoacoes.model.dto.ItemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Item;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private ItemMapper() {
    }

    public Item toItem(ItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Item item = new Item();

        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setCategoria(dto.getCategoria());
        item.setCondicao(dto.getCondicao());
        item.setLocalizacao(dto.getLocalizacao());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioId());
            item.setUsuario(usuario);
        }

        return item;
    }


    public void atualizarItemExistente(Item item, ItemRequestDTO dto) {
        if (item == null || dto == null) {
            return;
        }

        if (dto.getNome() != null) {
            item.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            item.setDescricao(dto.getDescricao());
        }
        if (dto.getCategoria() != null) {
            item.setCategoria(dto.getCategoria());
        }
        if (dto.getCondicao() != null) {
            item.setCondicao(dto.getCondicao());
        }
        if (dto.getLocalizacao() != null) {
            item.setLocalizacao(dto.getLocalizacao());
        }


    }
}
