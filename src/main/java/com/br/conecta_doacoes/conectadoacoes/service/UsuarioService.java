package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.exception.SenhasNaoCoincidemException;
import com.br.conecta_doacoes.conectadoacoes.model.dto.UsuarioDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.br.conecta_doacoes.conectadoacoes.model.mapper.UsuarioMapper;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    public void salvarUsuario(UsuarioDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new SenhasNaoCoincidemException("As senhas não coincidem");
        }
        Usuario usuario = usuarioMapper.converterParaEntity(dto, passwordEncoder);
        usuarioRepository.save(usuario);
    }

    public void editarUsuario(Long id, UsuarioDTO dto) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioMapper.atualizarUsuarioExistente(existente, dto, passwordEncoder);

        usuarioRepository.save(existente);
    }

    public void excluirUsuario(Long id) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(existente);
    }
}
