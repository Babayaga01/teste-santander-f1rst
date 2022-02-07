package com.f1rst.santander.contas.service;

import com.f1rst.santander.contas.dto.request.ClienteDTO;
import com.f1rst.santander.contas.dto.request.ContaDTO;
import com.f1rst.santander.contas.dto.response.ResponseDTO;
import com.f1rst.santander.contas.entity.Cliente;
import com.f1rst.santander.contas.entity.Conta;
import com.f1rst.santander.contas.exceptions.IdNotFoundException;
import com.f1rst.santander.contas.mapper.ClienteMapper;
import com.f1rst.santander.contas.mapper.ContaMapper;
import com.f1rst.santander.contas.repository.ClienteRepository;
import com.f1rst.santander.contas.repository.ContaRepository;
import com.f1rst.santander.contas.utils.CalculoCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;

    private final ContaMapper contaMapper = ContaMapper.INSTANCE;
    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    private static int DIGITO = 1;
    private static String CONTA = "101-";
    private static final String AGENCIA = "2636";

    @Autowired
    private ClienteService clienteService;

    @Autowired
    public ContaService(ContaRepository contaRepository) {

        this.contaRepository = contaRepository;
    }

    public ContaDTO findById(Long id) throws IdNotFoundException {
        Conta conta = existe_conta(id);

        return contaMapper.toDTO(conta);
    }

    public Conta createConta(Cliente cliente){

            Conta conta = new Conta();
            conta.setNumConta(geraConta());
            conta.setAgencia(AGENCIA);
            conta.setCliente(cliente);
            conta.setLimite_cartao(CalculoCredito.calculo(cliente.getSalario()));

        return  contaRepository.save(conta);

    }

    // metodo banco de dados [rever forma de adicionar o findAll de clienteService]
    public void /*List<ContaDTO>*/ createContaBancoDeDados(List<ClienteDTO> clientes){

        Conta conta = new Conta();
        Cliente cliente;

        for (ClienteDTO clienteDTO:clientes) {
            cliente = clienteMapper.toEntity(clienteDTO);
            conta = createConta(cliente);
            cliente.setConta(conta);
            clienteRepository.save(cliente);
        }

       // return (List<ContaDTO>) contaMapper.toDTO(conta);
    }

//    public void createClienteConta(List<Cliente> clientes){
//
//        Conta conta = new Conta();
//        Cliente cliente;
//
//        Iterator<Cliente> iteratorCliente = clientes.iterator();
//        while(iteratorCliente.hasNext()){
//            conta = createConta(iteratorCliente.next());
//            cliente.setConta(conta);
//            clienteRepository.save(cliente);
//        }

 //   }

    public Optional<Conta> findByClienteId(Long id) {

        return contaRepository.findByClienteId(id);
    }


    public Conta findByConta(String numConta) throws IdNotFoundException {

           //Conta conta = existe_conta(numConta);

           //return (conta);

        return contaRepository.findByNumConta(numConta).get();
    }

    private Conta existe_conta(Long id) throws IdNotFoundException {
        return contaRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    private Conta existe_conta(String numConta) throws IdNotFoundException {
        return contaRepository.findByNumConta(numConta)
                .orElseThrow(() -> new IdNotFoundException(numConta));
    }

    private String geraConta(){
        String digito = Integer.toString(DIGITO++);
        String numConta = this.CONTA + digito;
        Optional<Conta> conta = contaRepository.findByNumConta(numConta);
        if(conta.isPresent()){
            return geraConta();
        }else{
           return numConta;
        }
    }

//    private ResponseDTO responseDTO(String id, String msg){
//
//        return ResponseDTO.builder().response(msg + id).build();
//    }
}
