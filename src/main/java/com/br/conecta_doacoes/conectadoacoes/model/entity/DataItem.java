package com.br.conecta_doacoes.conectadoacoes.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    private String tipoArquivo;

    @Lob
    private byte[] imagemItem;

}
