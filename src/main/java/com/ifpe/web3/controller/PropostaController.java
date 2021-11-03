package com.ifpe.web3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifpe.web3.Morador;
import com.ifpe.web3.MoradorDAO;
import com.ifpe.web3.Proposta;
import com.ifpe.web3.PropostaDAO;

@Controller
public class PropostaController {
	
	private List<Proposta> listaProposta = new ArrayList<Proposta>();
	
	private List<Morador> listaMoradores = new ArrayList<Morador>();
	
	@Autowired
	private PropostaDAO propostaDAO;
	
	@Autowired
	private MoradorDAO moradorDAO;
	
	@ModelAttribute("listagemPropostas")
	public List<Proposta> getlistaProposta(){
		return this.propostaDAO.findAll(Sort.by("idProposta"));
	}
	
	@GetMapping("/cadastroProposta")
	public String exibirCadastroProposta (Proposta proposta, Model model) {
		model.addAttribute("listaMorador",
				this.moradorDAO.findAll(Sort.by("nomeMorador")));
		return "propostaPagina";
	}
	
	@PostMapping ("/salvarPropostaObj")
	public String salvarPropostaObj (Proposta proposta, RedirectAttributes redp){
		
		if (proposta.getNomeProposta().trim().isEmpty()) {
			redp.addFlashAttribute("mensagem", "Nome de proposta inválido!");
			return"redirect:/cadastroProposta";
		}
		if (proposta.getDescricao().trim().isEmpty()) {
			redp.addFlashAttribute("mensagem", "Descrição inválida!");
			return"redirect:/cadastroProposta";
		}
		
		if (this.propostaDAO.existsByNomeProposta(proposta.getNomeProposta())) {
			redp.addFlashAttribute("mensagem", "Já existe uma proposta com este nome!");
			return"redirect:/cadastroProposta";
		}
		
		
		this.propostaDAO.save(proposta);
		return "index";
	}
	
	//exibe a lista de propostas
	@GetMapping("/listagemPropostas")
	public String listaDePropostas() {
		return "listarProposta";
	}
	
	@GetMapping ("/alterarProposta")
	public String alterarProposta (Integer idProposta, Model model) {
		Proposta listP = this.propostaDAO.getOne(idProposta);
		model.addAttribute("proposta",listP);
		return "propostaPagina";
	}
	
	@GetMapping ("/excluirProposta")
	public String excluirProposta (Integer idProposta) {
		this.propostaDAO.deleteById(idProposta);
		return "redirect:/listagemPropostas";
	}
	
	@GetMapping ("/redirectPaginaInicial")
	public String voltaprapagina () {
		return"index";
	}
}
