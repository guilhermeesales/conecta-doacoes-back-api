package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.exception.UsuarioNaoEncontradoException;
import com.br.conecta_doacoes.conectadoacoes.model.dto.ItemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Item;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Categoria;
import com.br.conecta_doacoes.conectadoacoes.model.enums.Localizacao;
import com.br.conecta_doacoes.conectadoacoes.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<?> listarTodos() {
        try {
            List<Item> itens = itemService.listarTodos();
            if (itens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Nenhum item encontrado.");
            }
            return ResponseEntity.ok(itens);
        } catch (Exception e) {
            System.err.println("Erro ao listar todos os itens: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao listar itens.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Item item = itemService.buscarPorId(id);
            return ResponseEntity.ok(item);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao buscar item por ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar item.");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@ModelAttribute ItemRequestDTO dto) {
        try {
            itemService.salvarItem(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item cadastrado com sucesso!");
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem do item: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao cadastrar item.");
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@ModelAttribute ItemRequestDTO dto, @PathVariable Long id) {
        try {
            itemService.editarItem(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body("Item atualizado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) { // Catches the "Erro ao processar arquivo da imagem" from service
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao editar item com ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao editar item.");
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        try {
            itemService.excluirItem(id);
            return ResponseEntity.status(HttpStatus.OK).body("Item removido com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao remover item com ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao remover item.");
        }
    }

    // GET /api/itens/categoria/{categoria}
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> listarPorCategoria(@PathVariable("categoria") Categoria categoria) {
        try{
            List<Item> itens = itemService.listarPorCategoria(categoria);
            return ResponseEntity.ok(itens);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar item pela categoria.");
        }

    }

    // GET /api/itens/localizacao/{localizacao}
    @GetMapping("/localizacao/{localizacao}")
    public ResponseEntity<?> listarPorLocalizacao(@PathVariable("localizacao") Localizacao localizacao) {
        try{
            List<Item> itens = itemService.listarPorLocalizacao(localizacao);
            return ResponseEntity.ok(itens);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar item pela localização.");
        }
    }
}
