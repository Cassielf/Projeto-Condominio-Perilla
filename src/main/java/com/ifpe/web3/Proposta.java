package com.ifpe.web3;

import javax.persistence.*;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProposta;
	@Column(length = 100)
	private String nomeProposta;
	private String descricao;
	@Column(length = 6)
	private double orcamento;
	@Column(length = 4)
	private Integer anoPrevisto;
	@ManyToOne
	private Morador morador;
	
	public Integer getIdProposta() {
		return idProposta;
	}
	public void setIdProposta(Integer idProposta) {
		this.idProposta = idProposta;
	}
	public String getNomeProposta() {
		return nomeProposta;
	}
	public void setNomeProposta(String nomeProposta) {
		this.nomeProposta = nomeProposta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}
	public Integer getAnoPrevisto() {
		return anoPrevisto;
	}
	public void setAnoPrevisto(Integer anoPrevisto) {
		this.anoPrevisto = anoPrevisto;
	}

	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	@Override
	public String toString() {
		return nomeProposta;
	}
	
	

}
