package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioRegisterRequest;
import com.br.conecta_doacoes.conectadoacoes.service.PerfilUsuarioService;
import com.br.conecta_doacoes.conectadoacoes.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerfilUsuarioService perfilUsuarioService;

    public UsuarioController(UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder,
                             PerfilUsuarioService perfilUsuarioService) {
        this.usuarioService = usuarioService;
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @GetMapping("/obter-usuario-logado")
    public ResponseEntity<UsuarioRegisterRequest> obterUsuarioLogado() {
        String email = perfilUsuarioService.getUsuarioLogado();
        UsuarioRegisterRequest usuario = usuarioService.obterUsuarioLogado(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> register(@RequestBody UsuarioRegisterRequest user) {
        this.usuarioService.salvarUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestBody UsuarioRegisterRequest user, @PathVariable Long id) {
        this.usuarioService.editarUsuario(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        this.usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
