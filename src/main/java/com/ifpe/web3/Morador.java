package com.ifpe.web3;

import javax.persistence.*;

@Entity
public class Morador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMorador;
	@Column(length = 100)
	private String nomeMorador;
	@Column(length = 3)
	private String bloco;
	@Column(length = 3)
	private String apto;
	@Column(length = 9)
	private String telefone;
	
	public Integer getIdMorador() {
		return idMorador;
	}
	public void setIdMorador(Integer idMorador) {
		this.idMorador = idMorador;
	}
	public String getNomeMorador() {
		return nomeMorador;
	}
	public void setNomeMorador(String nomeMorador) {
		this.nomeMorador = nomeMorador;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getApto() {
		return apto;
	}
	public void setApto(String apto) {
		this.apto = apto;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return nomeMorador;
	}

	
}
