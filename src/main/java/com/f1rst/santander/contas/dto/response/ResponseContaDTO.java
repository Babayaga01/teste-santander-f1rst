package com.f1rst.santander.contas.dto.response;

import com.f1rst.santander.contas.entity.Conta;
import com.f1rst.santander.contas.utils.CalculoCredito;
import lombok.Data;

@Data
public class ResponseContaDTO {

    private String nome;

    private double salario;

    private String agencia;

    private String conta;

    private double limiteCredito;

    public ResponseContaDTO(Conta conta) {
        this.nome = conta.getCliente().getNome();
        this.salario = conta.getCliente().getSalario();
        this.agencia = conta.getAgencia();
        this.conta = conta.getNumConta();
        this.limiteCredito = CalculoCredito.calculo(conta.getCliente().getSalario());
    }

}
