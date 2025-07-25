package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.br.conecta_doacoes.conectadoacoes.model.dto.ItemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemRequestDTO dto);
    void atualizarItemExistente(@MappingTarget Item item, ItemRequestDTO dto);

}
