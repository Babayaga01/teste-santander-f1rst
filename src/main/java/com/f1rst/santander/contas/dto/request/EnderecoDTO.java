package com.f1rst.santander.contas.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EnderecoDTO {

    private Long id_endereco;

   @NotEmpty
   //@Pattern(regexp = "(\\d{2}) \\d{5}-\\d{3}")
    private String cep;

   @NotEmpty
   @Size(min = 5, max = 50)
    private String rua;

   //@NotEmpty
   //@Size(min = 1, max = 4)
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
