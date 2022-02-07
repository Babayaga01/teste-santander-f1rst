package com.f1rst.santander.contas.controller;

import com.f1rst.santander.contas.dto.request.ClienteDTO;
import com.f1rst.santander.contas.dto.response.ResponseDTO;
import com.f1rst.santander.contas.exceptions.IdNotFoundException;
import com.f1rst.santander.contas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("f1rst/clientes")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {

        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // retorna codigo http 201, seguindo padrão de verbos http
    public ResponseDTO create(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.create(clienteDTO);

    }

    @PutMapping("/{id}")
    public ResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) throws IdNotFoundException {
            return clienteService.updateById(id, clienteDTO);
    }

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable Long id) throws IdNotFoundException {
        return clienteService.findById(id);
    }

    @GetMapping
    public List<ClienteDTO> findAll(){

        return clienteService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws IdNotFoundException {
        clienteService.deleteById(id);
    }

    @GetMapping("/full")
    public void getClienteConta(){
        clienteService.gerarContas();
    }


}
