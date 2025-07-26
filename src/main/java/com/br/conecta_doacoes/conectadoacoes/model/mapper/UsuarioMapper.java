package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioRegisterRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    private UsuarioMapper() {
    }


    public Usuario toUsuario(UsuarioRegisterRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setPassword(dto.getPassword());

        if (dto.getRole() != null) {
            usuario.setRole(dto.getRole());
        }

        return usuario;
    }


    public UsuarioRegisterRequestDTO toUsuarioRegisterRequest(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioRegisterRequestDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .role(usuario.getRole())
                .build();
    }


    public static void atualizarUsuarioExistente(Usuario usuario, UsuarioRegisterRequestDTO dto) {
        if (usuario == null || dto == null) {
            return;
        }

        if (dto.getNome() != null) {
            usuario.setNome(dto.getNome());
        }
        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getTelefone() != null) {
            usuario.setTelefone(dto.getTelefone());
        }
        // A lógica de validação e codificação da nova senha deve ocorrer no Service.
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuario.setPassword(dto.getPassword());
        }
        if (dto.getRole() != null) {
            usuario.setRole(dto.getRole());
        }
    }

}
