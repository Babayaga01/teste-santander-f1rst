package com.f1rst.santander.contas.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TelefoneDTO {

    private Long id_telefone;

    @Size(min = 10, max = 12)
    private String tel_residencial;

    @NotEmpty
    @Size(min = 11, max = 13)
    private String tel_celular;
}
