package com.f1rst.santander.contas.dto.request;

import com.f1rst.santander.contas.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

    private Long id;

    private String numConta;

    private String agencia;

    private double limite_cartao;

    @Valid
    private Cliente cliente;

}
