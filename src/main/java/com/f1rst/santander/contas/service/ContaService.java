package com.f1rst.santander.contas.service;

import com.f1rst.santander.contas.dto.request.ContaDTO;
import com.f1rst.santander.contas.entity.Cliente;
import com.f1rst.santander.contas.entity.Conta;
import com.f1rst.santander.contas.exceptions.IdNotFoundException;
import com.f1rst.santander.contas.mapper.ContaMapper;
import com.f1rst.santander.contas.repository.ContaRepository;
import com.f1rst.santander.contas.utils.CalculoCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    private ContaRepository contaRepository;

    private final ContaMapper contaMapper = ContaMapper.INSTANCE;

    private static int DIGITO = 1;
    private static String CONTA = "101-";
    private static final String AGENCIA = "2636";


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

    public Optional<Conta> findByClienteId(Long id) {

        return contaRepository.findByClienteId(id);
    }


    public Conta findByConta(String numConta) throws IdNotFoundException {

           Conta conta = existe_conta(numConta);

           return (conta);
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
}
