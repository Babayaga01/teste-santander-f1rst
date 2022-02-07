package com.f1rst.santander.contas.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EnderecoDTO {

    private Long id_endereco;

   @NotEmpty
    private String cep;

   @NotEmpty
   @Size(min = 5, max = 50)
    private String rua;

    private int numero;

   @NotEmpty
   @Size(min = 5, max = 50)
    private String bairro;

   @NotEmpty
   @Size(min = 5, max = 50)
    private String cidade;

   @NotEmpty
   @Size(min = 2, max = 2)
    private String estado;
}
