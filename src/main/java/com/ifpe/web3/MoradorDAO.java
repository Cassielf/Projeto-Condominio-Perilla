package com.ifpe.web3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorDAO extends JpaRepository<Morador, Integer> {

	boolean existsByNomeMorador(String nomeMorador);

	//List<Morador> findbyMoradors(String nomeMorador);
}
