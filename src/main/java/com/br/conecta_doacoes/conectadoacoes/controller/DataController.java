package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.model.dto.ImagemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.service.DataItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagens")
public class DataController {

    private final DataItemService dataItemService;

    public DataController(DataItemService dataItemService) {
        this.dataItemService = dataItemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ImagemRequestDTO> getImagemPorItemId(@PathVariable Long itemId) {
        ImagemRequestDTO imagemDTO = dataItemService.getImagemPorItemId(itemId);
        return ResponseEntity.ok(imagemDTO);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ImagemRequestDTO>> listarImagensPorUsuario(@PathVariable Long usuarioId) {
        List<ImagemRequestDTO> imagens = dataItemService.listarImagensPorUsuarioId(usuarioId);
        return ResponseEntity.ok(imagens);
    }
}
