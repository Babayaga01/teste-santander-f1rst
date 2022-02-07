package com.f1rst.santander.contas.service;

import com.f1rst.santander.contas.dto.request.ClienteDTO;
import com.f1rst.santander.contas.dto.response.ResponseDTO;
import com.f1rst.santander.contas.entity.Cliente;
import com.f1rst.santander.contas.entity.Conta;
import com.f1rst.santander.contas.exceptions.IdNotFoundException;
import com.f1rst.santander.contas.mapper.ClienteMapper;
import com.f1rst.santander.contas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Autowired
    private ContaService contaService;


    public ResponseDTO create(ClienteDTO clienteDTO){

        Cliente clienteToSave = clienteMapper.toEntity(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(clienteToSave);

        if(contaService.findByClienteId(clienteSalvo.getId()).isEmpty()){

            Conta conta = contaService.createConta(clienteSalvo);
            clienteSalvo.setConta(conta);
            clienteSalvo = clienteRepository.save(clienteToSave);

        }

        return responseDTO(clienteSalvo.getId(), "Cliente criado com o ID: ");
    }

    public ClienteDTO findById(Long id) throws IdNotFoundException {
        Cliente cliente = existe_cliente(id);

        return clienteMapper.toDTO(cliente);
    }

    public List<ClienteDTO> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
    }

    public List<Cliente> findAll_2(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public void gerarContas(){
        List<Cliente> clientes = findAll_2();

        for (Cliente cliente:clientes) {
            if(contaService.findByClienteId(cliente.getId()).isEmpty()) {
                Conta conta = contaService.createConta(cliente);
                cliente.setConta(conta);
                cliente = clienteRepository.save(cliente);
            }
        }
    }

    public void deleteById(Long id) throws IdNotFoundException {
        existe_cliente(id);
        clienteRepository.deleteById(id);
    }

    public ResponseDTO updateById(Long id, ClienteDTO clienteDTO) throws IdNotFoundException {

        existe_cliente(id);
        Cliente clienteToUpdate = clienteMapper.toEntity(clienteDTO);
        Cliente updatedCliente = clienteRepository.save(clienteToUpdate);

        return responseDTO(updatedCliente.getId(), "Cliente atualizado com o ID ");
    }

    private ResponseDTO responseDTO(Long id, String msg){

        return ResponseDTO.builder().response(msg + id).build();
    }

    private Cliente existe_cliente(Long id) throws IdNotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

}
