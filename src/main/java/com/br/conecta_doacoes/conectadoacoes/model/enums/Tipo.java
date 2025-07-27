package com.br.conecta_doacoes.conectadoacoes.model.enums;

public enum Tipo {
    TROCA("Troca"),
    DOACAO("Doação"),;

    private String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;

    }
}
