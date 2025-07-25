package com.br.conecta_doacoes.conectadoacoes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegisterRequestDTO {
    private String email;
    private String nome;
    private String password;
    private String confirmPassword;
    private String telefone;
    private String role;
}
