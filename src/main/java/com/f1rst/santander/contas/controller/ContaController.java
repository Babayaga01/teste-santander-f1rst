package com.f1rst.santander.contas.controller;

import com.f1rst.santander.contas.dto.request.ClienteDTO;
import com.f1rst.santander.contas.dto.request.ContaDTO;
import com.f1rst.santander.contas.dto.response.ResponseContaDTO;
import com.f1rst.santander.contas.entity.Cliente;
import com.f1rst.santander.contas.entity.Conta;
import com.f1rst.santander.contas.exceptions.IdNotFoundException;
import com.f1rst.santander.contas.service.ClienteService;
import com.f1rst.santander.contas.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("f1rst/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;


    @GetMapping(value = "{conta}")
    public ResponseContaDTO getDados(@PathVariable String conta) throws IdNotFoundException {

         Conta contaObj = contaService.findByConta(conta);

        return new ResponseContaDTO(contaObj); //contaObj
    }

//    @GetMapping("/full")
//    public void getDadosBanco(){
//        List<ClienteDTO> clientes = clienteService.findAll();
//        /*List<ContaDTO> response =*/ contaService.createContaBancoDeDados(clientes);
//        //return (List<ResponseContaDTO>) new ResponseContaDTO(response);
//    }


}
