package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.exception.SenhasNaoCoincidemException;
import com.br.conecta_doacoes.conectadoacoes.exception.UsuarioJaCadastradoException;
import com.br.conecta_doacoes.conectadoacoes.exception.UsuarioNaoEncontradoException;
import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioRegisterRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.service.PerfilUsuarioService;
import com.br.conecta_doacoes.conectadoacoes.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerfilUsuarioService perfilUsuarioService;

    public UsuarioController(UsuarioService usuarioService,
                             PerfilUsuarioService perfilUsuarioService) { // Removed BCryptPasswordEncoder from constructor as it's not used directly here
        this.usuarioService = usuarioService;
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @GetMapping("/obter-usuario-logado")
    public ResponseEntity<?> obterUsuarioLogado() {
        try {
            String email = perfilUsuarioService.getUsuarioLogado();
            UsuarioRegisterRequestDTO usuario = usuarioService.obterUsuarioLogado(email);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao obter usuário logado.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obterTodosUsuarios() {
        try {
            List<UsuarioRegisterRequestDTO> usuarios = usuarioService.obterTodosUsuarios();
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Nenhum usuário encontrado.");
            }
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao obter todos os usuários.");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> register(@RequestBody UsuarioRegisterRequestDTO user) {
        try {
            this.usuarioService.salvarUsuario(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        } catch (SenhasNaoCoincidemException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UsuarioJaCadastradoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao cadastrar usuário.");
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestBody UsuarioRegisterRequestDTO user, @PathVariable Long id) {
        try {
            this.usuarioService.editarUsuario(id, user);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SenhasNaoCoincidemException e) { // If you implement password confirmation in edit, handle this
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao editar usuário.");
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        try {
            this.usuarioService.excluirUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário removido com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao remover usuário.");
        }
    }
}