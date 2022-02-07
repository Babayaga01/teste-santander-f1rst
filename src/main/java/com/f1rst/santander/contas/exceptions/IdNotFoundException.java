package com.f1rst.santander.contas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends Exception{

    public IdNotFoundException(Long id){

        super("ID não encontrado");
    }

    public IdNotFoundException(String numConta){

        super("ID não encontrado");
    }

}
