package com.br.conecta_doacoes.conectadoacoes.controller;

import com.br.conecta_doacoes.conectadoacoes.model.Usuario;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario user) {
        if (usuarioRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usuarioRepository.save(user);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @GetMapping("/rota-protegida")
    public ResponseEntity<String> rotaProtegida() {
        return ResponseEntity.ok().body("OLA AQUI A ROTA EH PROTEGIDA");
    }

}
