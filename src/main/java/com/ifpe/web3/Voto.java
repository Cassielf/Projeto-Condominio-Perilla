package com.ifpe.web3;

import java.time.LocalDate;
import com.ifpe.web3.Proposta;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVoto;
	private boolean confirmado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVoto;
	@Column(length = 100)
	private String nomeMoradorV;
	@ManyToOne
	private Proposta proposta;
	
	
	public Integer getIdVoto() {
		return idVoto;
	}
	public void setIdVoto(Integer idVoto) {
		this.idVoto = idVoto;
	}
	
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	public boolean isConfirmado() {
		return confirmado;
	}
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
	public LocalDate getDataVoto() {
		return dataVoto;
	}
	public void setDataVoto(LocalDate dataVoto) {
		this.dataVoto = dataVoto;
	}
	public String getNomeMoradorV() {
		return nomeMoradorV;
	}
	public void setNomeMoradorV(String nomeMoradorV) {
		this.nomeMoradorV = nomeMoradorV;
	}

	
}
