package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

/***
 * 
 * @author 81612334 Leonardo Santiago Gonçalves SIN3AN-MCA1
 *  */

@Service
public class FilaService {
	private FilaDAO dao;
	
	@Autowired
	public FilaService(FilaDAO dao) {
		this.dao = dao;
	}
	public ArrayList<Fila> listarFilas() throws IOException{
				
		return dao.listarFilas();
	}
	
	
	public Fila carregar(int id) throws IOException{
		// TODO 4.3. Complete o método carregar(int id) da classe FilaService 
		//que deve retornar um objeto fila com o nome e o id.
		Fila fila = dao.Carregar(id);
		
		return fila;
	}
}
