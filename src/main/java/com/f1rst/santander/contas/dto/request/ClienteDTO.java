package com.f1rst.santander.contas.dto.request;

import com.f1rst.santander.contas.entity.Conta;
import com.f1rst.santander.contas.entity.Endereco;
import com.f1rst.santander.contas.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id_cliente;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String nome;

    private int idade;

    @NotEmpty
    @CPF
    private String cpf;

    @Size(min = 7, max = 9)
    private String rg;

    private String data_nascimento;

    @Size(min = 3, max = 10)
    private String sexo;

    @Size(min = 3, max = 30)
    private String mae;

    @Size(min = 3, max = 30)
    private String pai;

    private double salario;

    @Valid
    private Telefone telefone;

    @Valid
    private Endereco endereco;

    @Valid
    private Conta conta;
}
