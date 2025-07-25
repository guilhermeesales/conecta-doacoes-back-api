package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioRegisterRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role", defaultValue = "USER")
    Usuario toUsuario(UsuarioRegisterRequestDTO dto);

    @Mapping(target = "confirmPassword", ignore = true)
    @Mapping(target = "password", ignore = true)
    UsuarioRegisterRequestDTO toUsuarioRegisterRequest(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "password", source = "password")
    void atualizarUsuarioExistente(@MappingTarget Usuario usuario, UsuarioRegisterRequestDTO dto);
}
