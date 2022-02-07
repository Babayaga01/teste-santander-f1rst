package com.f1rst.santander.contas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_conta;

    @Column(unique = true, nullable = false)
    private String numConta;

    @Column
    private String agencia;

    @Column
    private Double limite_cartao;

   // @Column
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


}
