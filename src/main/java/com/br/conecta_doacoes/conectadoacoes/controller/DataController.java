package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.model.dto.ImagemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.service.DataItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/imagens")
public class DataController {

    private final DataItemService dataItemService;

    public DataController(DataItemService dataItemService) {
        this.dataItemService = dataItemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getImagemPorItemId(@PathVariable Long itemId) {
        try {
            ImagemRequestDTO imagemDTO = dataItemService.getImagemPorItemId(itemId);
            return ResponseEntity.ok(imagemDTO);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getReason());
        } catch (Exception e) {
            System.err.println("Erro interno ao obter imagem para o item com ID " + itemId + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao obter imagem.");
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarImagensPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<ImagemRequestDTO> imagens = dataItemService.listarImagensPorUsuarioId(usuarioId);
            if (imagens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Nenhuma imagem encontrada para o usuário com ID: " + usuarioId);
            }
            return ResponseEntity.ok(imagens);
        } catch (Exception e) {
            // Catch any unexpected exceptions
            System.err.println("Erro interno ao listar imagens para o usuário com ID " + usuarioId + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao listar imagens por usuário.");
        }
    }
}
