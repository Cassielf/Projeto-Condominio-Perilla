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

@Controller
public class MoradorController {
	
	private List <Morador> listaMorador = new ArrayList <Morador>();
	
	@Autowired
	private MoradorDAO moradorDAO;
	
	//preenche os dados da lista de moradores
	@ModelAttribute ("listagemMorador")
	public List<Morador> getlistaMorador(){
		return this.moradorDAO.findAll(Sort.by("nomeMorador"));
	}
	
	@GetMapping ("/cadastroMorador")
	public String exibirCadastroMorador (Morador morador) {
		return "moradorPagina";
	}
	
	//Vai para a lista
	@GetMapping ("/listagemMoradores")
	public String listaM () {
		return "listarMorador";
	}
	
	@PostMapping ("/salvarMoradorObj")
	public String salvarMoradorobj (Morador morador, RedirectAttributes red) {
		
		if (morador.getNomeMorador().trim().isEmpty()) {
			red.addFlashAttribute("mensagem", "Nome inválido!");
		
			return"redirect:/cadastroMorador";
		}
		if (morador.getBloco().trim().isEmpty()) {
			red.addFlashAttribute("mensagem", "Bloco inválido!");
		
			return"redirect:/cadastroMorador";
		}
		
		if (morador.getApto().trim().isEmpty()) {
			red.addFlashAttribute("mensagem", "Apartamento inválido");
		
			return"redirect:/cadastroMorador";
		}
		
		if (this.moradorDAO.existsByNomeMorador(morador.getNomeMorador())) {
			red.addFlashAttribute("mensagem", "Morador cadastrado!");
			red.addFlashAttribute("morador", morador);
			
			return"redirect:/cadastroMorador";
		}

		
		this.moradorDAO.save(morador);
		return "index";
	}
	
	@GetMapping ("/alterarMorador")
	public String alterarMorador (Integer idMorador, Model model) {
		Morador list = this.moradorDAO.getOne(idMorador);
		model.addAttribute("morador", list);
		return "moradorPagina";
	}
	
	@GetMapping ("/excluirMorador")
	public String excluirMorador(Integer idMorador) {
		this.moradorDAO.deleteById(idMorador);
		return "redirect:/listagemMoradores";
	}
}
