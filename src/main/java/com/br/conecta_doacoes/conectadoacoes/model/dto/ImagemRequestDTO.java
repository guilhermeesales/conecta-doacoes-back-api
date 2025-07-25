package com.br.conecta_doacoes.conectadoacoes.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImagemRequestDTO {
    private Long itemId;
    private String imagem;

}
