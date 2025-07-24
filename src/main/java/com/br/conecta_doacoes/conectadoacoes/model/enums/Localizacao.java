package com.br.conecta_doacoes.conectadoacoes.model.enums;

public enum Localizacao {
    CENTRO("Centro"),
    CIDADE_UNIVERSITARIA("Cidade Universitaria"),
    VILA_MATOSO("Vila Matoso"),
    GUANABARA("Guanabara");

    private String localizacao;

    Localizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
