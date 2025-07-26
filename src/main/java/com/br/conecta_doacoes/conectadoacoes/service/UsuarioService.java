package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.exception.SenhasNaoCoincidemException;
import com.br.conecta_doacoes.conectadoacoes.exception.UsuarioJaCadastradoException;
import com.br.conecta_doacoes.conectadoacoes.exception.UsuarioNaoEncontradoException;
import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioRegisterRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.br.conecta_doacoes.conectadoacoes.model.mapper.UsuarioMapper;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void salvarUsuario(UsuarioRegisterRequestDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new SenhasNaoCoincidemException("As senhas não coincidem");
        }

        if(usuarioRepository.findByEmail(dto.getEmail()) != null){
            throw new UsuarioJaCadastradoException("Usuario ja cadastrado");
        }

        Usuario usuario = usuarioMapper.toUsuario(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        System.out.println(usuario);
        usuarioRepository.save(usuario);
    }

    public UsuarioRegisterRequestDTO obterUsuarioLogado(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null) {
            throw new UsuarioNaoEncontradoException("O email informado nao foi encontrado");
        }

        return usuarioMapper.toUsuarioRegisterRequest(usuario);
    }


    public List<UsuarioRegisterRequestDTO> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toUsuarioRegisterRequest)
                .collect(Collectors.toList());
    }

    public void editarUsuario(Long id, UsuarioRegisterRequestDTO dto) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioMapper.atualizarUsuarioExistente(existente, dto);
        existente.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuarioRepository.save(existente);
    }

    public void excluirUsuario(Long id) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(existente);
    }
}
