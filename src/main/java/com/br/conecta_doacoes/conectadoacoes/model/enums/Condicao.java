package com.br.conecta_doacoes.conectadoacoes.model.enums;

public enum Condicao {
    NOVO("Novo"),
    USADO("Usado"),
    SEMINOVO("Seminovo"),;

    private String descricao;

    Condicao(String descricao) {
        this.descricao = descricao;
    }

}
