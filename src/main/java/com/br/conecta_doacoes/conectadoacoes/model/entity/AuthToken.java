package com.br.conecta_doacoes.conectadoacoes.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private String email;

    private LocalDateTime createdAt;

    public AuthToken(String token, String email) {
        this.token = token;
        this.email = email;
    }

}
