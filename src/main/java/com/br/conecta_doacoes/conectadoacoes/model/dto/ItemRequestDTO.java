package com.br.conecta_doacoes.conectadoacoes.model.dto;

import com.br.conecta_doacoes.conectadoacoes.model.enums.Categoria;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Condicao;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemRequestDTO {
    private String nome;
    private String descricao;
    private Categoria categoria;
    private Condicao condicao;
    private Localizacao localizacao;
    private Long usuarioId;
    private MultipartFile arquivoImagem;

}
