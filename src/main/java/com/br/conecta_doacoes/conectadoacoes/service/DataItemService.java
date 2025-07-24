package com.br.conecta_doacoes.conectadoacoes.service;

import com.br.conecta_doacoes.conectadoacoes.model.mapper.DataItemMapper;
import com.br.conecta_doacoes.conectadoacoes.repository.DataItemRepository;
import org.springframework.stereotype.Service;

@Service
public class DataItemService {

    private final DataItemRepository dataItemRepository;
    private final DataItemMapper dataItemMapper;

    public DataItemService(DataItemRepository dataItemRepository, DataItemMapper dataItemMapper) {
        this.dataItemRepository = dataItemRepository;
        this.dataItemMapper = dataItemMapper;
    }

}
