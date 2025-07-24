package com.br.conecta_doacoes.conectadoacoes.model.entity;

import com.br.conecta_doacoes.conectadoacoes.model.enums.Categoria;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Condicao;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Categoria categoria;

    private Condicao condicao;

    private Localizacao localizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}
