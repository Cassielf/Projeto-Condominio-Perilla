package com.ifpe.web3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoDAO extends JpaRepository<Voto, Integer> {

	boolean existsByNomeMoradorV(String nomeMoradorV);

}
