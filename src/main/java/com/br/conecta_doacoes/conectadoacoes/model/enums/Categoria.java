package com.br.conecta_doacoes.conectadoacoes.model.enums;

public enum Categoria {
    ELETRONICO("Eletrônico"),
    LIVRO("Livro"),
    MOVEL("Movel"),
    ROUPA("Roupa");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }


}
