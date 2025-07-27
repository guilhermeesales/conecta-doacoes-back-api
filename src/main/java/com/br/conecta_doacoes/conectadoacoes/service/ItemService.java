package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.exception.UsuarioNaoEncontradoException;
import com.br.conecta_doacoes.conectadoacoes.model.dto.ItemRequestDTO;
import com.br.conecta_doacoes.conectadoacoes.model.entity.DataItem;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Item;
import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.br.conecta_doacoes.conectadoacoes.model.mapper.ItemMapper;
import com.br.conecta_doacoes.conectadoacoes.repository.ItemRepository;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, UsuarioRepository usuarioRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional
    public void salvarItem(ItemRequestDTO dto) throws IOException {
        log.info("Salvando item:");
        log.info("Nome: {}", dto.getNome());
        log.info("Categoria: {}", dto.getCategoria());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        MultipartFile arquivo = dto.getArquivoImagem();
        if (arquivo == null || arquivo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo da imagem está vazio ou ausente");
        }

        DataItem dataItem = new DataItem();
        dataItem.setNomeArquivo(arquivo.getOriginalFilename());
        dataItem.setTipoArquivo(arquivo.getContentType());
        dataItem.setImagemItem(arquivo.getBytes());

        Item item = itemMapper.toItem(dto);
        item.setUsuario(usuario);
        item.setDataItem(dataItem);

        itemRepository.save(item);
    }



    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    public Item buscarPorId(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
    }

    public void editarItem(Long id, ItemRequestDTO dto) {
        Item existente = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        itemMapper.atualizarItemExistente(existente, dto);

        existente.setUsuario(usuario);

        DataItem dataItem = existente.getDataItem();
        if (dataItem == null) {
            dataItem = new DataItem();
            existente.setDataItem(dataItem);
        }

        MultipartFile arquivo = dto.getArquivoImagem();

        if (arquivo != null && !arquivo.isEmpty()) {
            try {
                dataItem.setNomeArquivo(arquivo.getOriginalFilename());
                dataItem.setTipoArquivo(arquivo.getContentType());
                dataItem.setImagemItem(arquivo.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar arquivo da imagem", e);
            }
        }

        itemRepository.save(existente);
    }


    public void excluirItem(Long id) {
        Item existente = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
        itemRepository.delete(existente);
    }
}
