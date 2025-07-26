package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.model.dto.ItemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Item;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Categoria;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Localizacao;
import com.br.conecta_doacoes.conectadoacoes.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> listarTodos() {
        List<Item> itens = itemService.listarTodos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Item item = itemService.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping(value = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> cadastrar(@ModelAttribute ItemRequestDTO dto) throws IOException {
        System.out.println(">>> DTO recebido:");
        System.out.println(" nome       = " + dto.getNome());
        System.out.println(" descricao  = " + dto.getDescricao());
        System.out.println(" categoria  = " + dto.getCategoria());
        System.out.println(" condicao   = " + dto.getCondicao());
        System.out.println(" localizacao= " + dto.getLocalizacao());
        System.out.println(" usuarioId  = " + dto.getUsuarioId());
        System.out.println(" arquivo    = " + (dto.getArquivoImagem() != null));
        itemService.salvarItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestBody ItemRequestDTO dto, @PathVariable Long id) {
        itemService.editarItem(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        itemService.excluirItem(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/itens/categoria/{categoria}
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Item>> listarPorCategoria(@PathVariable("categoria") Categoria categoria) {
        List<Item> itens = itemService.listarPorCategoria(categoria);
        return ResponseEntity.ok(itens);
    }

    // GET /api/itens/localizacao/{localizacao}
    @GetMapping("/localizacao/{localizacao}")
    public ResponseEntity<List<Item>> listarPorLocalizacao(@PathVariable("localizacao") Localizacao localizacao) {
        List<Item> itens = itemService.listarPorLocalizacao(localizacao);
        return ResponseEntity.ok(itens);
    }
}
