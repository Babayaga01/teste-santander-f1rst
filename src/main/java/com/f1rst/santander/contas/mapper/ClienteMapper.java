package com.f1rst.santander.contas.mapper;

import com.f1rst.santander.contas.dto.request.ClienteDTO;
import com.f1rst.santander.contas.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "data_nascimento", source = "data_nascimento", dateFormat = "dd-MM-yyyy")
    Cliente toEntity(ClienteDTO clienteDTO);

    ClienteDTO toDTO(Cliente cliente);

}
