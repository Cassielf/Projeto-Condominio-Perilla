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

import com.ifpe.web3.PropostaDAO;
import com.ifpe.web3.Voto;
import com.ifpe.web3.VotoDAO;

@Controller
public class VotoController {
	
	private List<Voto> listaVoto = new ArrayList<Voto>();
	
	@Autowired
	private VotoDAO votoDAO;
	
	@Autowired
	private PropostaDAO propostaDAO;
	
	//Mostra os votos registrados
	@ModelAttribute("listagemVoto")
	public List<Voto> getlistaVoto(){
		return this.votoDAO.findAll(Sort.by("nomeMoradorV")); //verificar como serao ordenados os votos
	}
	
	
	@GetMapping("/cadastroVoto")
	public String exibirCadastroVoto(Voto voto, Model model) {
		model.addAttribute("selecionarProposta",
				this.propostaDAO.findAll(Sort.by("nomeProposta")));
		return"votoPagina";
	}
	
	@PostMapping ("/salvarVotoObj")
	public String salvarVotoObj (Voto voto, RedirectAttributes redv) {
		if (voto.getNomeMoradorV().trim().isEmpty()) {
			redv.addFlashAttribute("mensagem", "Nome inválido!");
			return"redirect:/cadastroVoto";
		}
		
		if (this.votoDAO.existsByNomeMoradorV(voto.getNomeMoradorV())) {
			redv.addFlashAttribute("mensagem", "Já existe uma pessoa com este nome!");
			redv.addFlashAttribute("voto", voto);
			return"redirect:/cadastroVoto";
		}
		
		
		this.votoDAO.save(voto);
		return"index";
	}
	
	//Vai para a lista
	@GetMapping("/listagemVotos")
	public String listaV() {
		return "listarVoto";
	}
	
	@GetMapping ("/alterarVoto")
	public String alterarVoto (Integer idVoto, Model model) {
		Voto listV = this.votoDAO.getOne(idVoto);
		model.addAttribute("voto", listV);
		return "votoPagina";
	}
	
	@GetMapping ("/excluirVoto")
		public String excluirVoto (Integer idVoto) {
		this.votoDAO.deleteById(idVoto);
		return "redirect:/listagemVotos";
	}
	
	@GetMapping ("/voltarPaginaInicial")
	public String voltarPaginaInicial () {
		return "index";
	}
}
