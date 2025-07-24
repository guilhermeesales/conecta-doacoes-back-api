package com.br.conecta_doacoes.conectadoacoes.controller;

import com.amazonaws.Response;
import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import com.br.conecta_doacoes.conectadoacoes.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO user) {
        this.usuarioService.salvarUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestBody UsuarioDTO user, @PathVariable Long id) {
        this.usuarioService.editarUsuario(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        this.usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
