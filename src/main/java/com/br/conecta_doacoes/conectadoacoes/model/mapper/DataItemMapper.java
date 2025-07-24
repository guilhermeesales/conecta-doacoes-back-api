package com.br.conecta_doacoes.conectadoacoes.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataItemMapper {
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public DataItemMapper(ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

}
