package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.br.conecta_doacoes.conectadoacoes.exception.SenhasNaoCoincidemException;
import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;


    public UsuarioMapper(ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    public Usuario converterParaEntity(UsuarioDTO dto, PasswordEncoder passwordEncoder) {

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setId(dto.getId());
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRole("USER");

        return usuario;
    }

    public void atualizarUsuarioExistente(Usuario existente, UsuarioDTO dto, PasswordEncoder passwordEncoder) {
        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            if (!dto.getPassword().equals(dto.getConfirmPassword())) {
                throw new SenhasNaoCoincidemException("As senhas não coincidem");
            }
            existente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }

}
