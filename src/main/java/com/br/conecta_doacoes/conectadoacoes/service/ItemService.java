package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.model.mapper.ItemMapper;
import com.br.conecta_doacoes.conectadoacoes.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

}
