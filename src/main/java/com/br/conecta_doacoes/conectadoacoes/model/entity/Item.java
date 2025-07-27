package com.br.conecta_doacoes.conectadoacoes.model.entity;

import com.br.conecta_doacoes.conectadoacoes.model.enums.Categoria;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Condicao;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Localizacao;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Condicao condicao;

    @Enumerated(EnumType.STRING)
    private Localizacao localizacao;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "data_item_id", nullable = false)
    @JsonManagedReference(value = "item-data")
    private DataItem dataItem;
}
