package br.usjt.arqsw.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/***
 * 
 * @author 81612334 Leonardo Santiago Gon�alves SIN3AN-MCA1
 *  */

@Controller
public class ManterChamadosController {
	
	private FilaService filaService;
	private ChamadoService chamadoService;
		
	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
		
	}

	/**
	 * 
	 * @return
	 */
	
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException{
			return filaService.listarFilas();
	}
		
		
	
	/**
	 * 
	 * @param model Acesso à request http
	 * @return JSP de Listar Chamados
	 */
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
		
	@RequestMapping("/listar_filas")
	public String novoChamado(Model model){
		try{
					
			model.addAttribute("lista",listarFilas());			
			return "NovoChamado";
		}catch(IOException e){
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/novo_chamado")
	public String criarChamado(@Valid Chamado chamado, BindingResult result, Model model) {
			try {
				int id = chamadoService.criarChamado(chamado);
				chamado.setId(id);
				return "ChamadoCriadoComSucesso";
			} catch (IOException e) {
				e.printStackTrace();
				return "Erro";
			}
	}

	
	//TODO Complete o m�todo listarChamadosExibir do controller. 
	
	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());	
			model.addAttribute("fila", fila);
			
			ArrayList<Chamado> chamados = chamadoService.listarChamados(fila);
			model.addAttribute("chamados", chamados);
			
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}	
	
}
