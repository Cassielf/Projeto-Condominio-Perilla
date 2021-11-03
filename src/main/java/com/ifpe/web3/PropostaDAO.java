package com.ifpe.web3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaDAO extends JpaRepository<Proposta, Integer> {

	boolean existsByNomeProposta(String nomeProposta);

}
