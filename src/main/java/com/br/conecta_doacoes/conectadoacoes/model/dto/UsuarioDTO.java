package com.br.conecta_doacoes.conectadoacoes.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String password;
    private String confirmPassword;
}
