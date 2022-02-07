package com.f1rst.santander.contas.repository;

import com.f1rst.santander.contas.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumConta(String conta);

    Optional<Conta> findByClienteId(Long id);
}
