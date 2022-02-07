package com.f1rst.santander.contas.mapper;

import com.f1rst.santander.contas.dto.request.ContaDTO;
import com.f1rst.santander.contas.entity.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContaMapper {

    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    Conta toEntity(ContaDTO contaDTO);

    ContaDTO toDTO(Conta conta);

}
