package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.model.dto.ImagemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.DataItem;
import com.br.conecta_doacoes.conectadoacoes.repository.DataItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DataItemService {

    private final DataItemRepository dataItemRepository;

    public DataItemService(DataItemRepository dataItemRepository) {
        this.dataItemRepository = dataItemRepository;
    }

    @Transactional(readOnly = true)
    public ImagemRequestDTO getImagemPorItemId(Long itemId) {
        DataItem dataItem = dataItemRepository.findByItemId(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Imagem não encontrada para o item com ID: " + itemId
                ));

        String imagemBase64 = converterParaDataUrl(dataItem.getImagemItem(), dataItem.getTipoArquivo());

        return new ImagemRequestDTO(
                dataItem.getItem().getId(),
                imagemBase64
        );
    }

    @Transactional(readOnly = true)
    public List<ImagemRequestDTO> listarImagensPorUsuarioId(Long usuarioId) {
        return dataItemRepository.findByItemUsuarioId(usuarioId)
                .stream()
                .map(dataItem -> new ImagemRequestDTO(
                        dataItem.getItem().getId(),
                        converterParaDataUrl(dataItem.getImagemItem(), dataItem.getTipoArquivo())
                ))
                .collect(Collectors.toList());
    }

    private String converterParaDataUrl(byte[] imagemBytes, String tipoArquivo) {
        if (imagemBytes == null || imagemBytes.length == 0) {
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(imagemBytes);
        return "data:"+  tipoArquivo + " ;base64," + base64;
    }
}
