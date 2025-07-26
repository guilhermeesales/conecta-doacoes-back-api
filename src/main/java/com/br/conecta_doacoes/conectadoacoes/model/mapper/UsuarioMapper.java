package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioRegisterRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioRegisterRequestDTO dto);

    UsuarioRegisterRequestDTO toUsuarioRegisterRequest(Usuario usuario);


    void atualizarUsuarioExistente(@MappingTarget Usuario usuario, UsuarioRegisterRequestDTO dto);
}
