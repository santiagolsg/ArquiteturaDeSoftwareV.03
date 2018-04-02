package br.usjt.arqsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/***
 * 
 * @author 81612334 Leonardo Santiago Gonçalves SIN3AN-MCA1
 *  */
@Entity
public class Chamado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ABERTO = "aberto";
	public static final String FECHADO = "fechado";
	
	private int id;   
	
	@NotNull(message="A Descrição nÃ£o pode ser vazia")	
	private String Descricao;  
	
	private String Status;    
	private Date DT_ABERTURA; 	
	private Date DT_FECHAMENTO;   
	private Fila fila;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getDT_ABERTURA() {
		return DT_ABERTURA;
	}

	public void setDT_ABERTURA(Date dT_ABERTURA) {
		DT_ABERTURA = dT_ABERTURA;
	}

	public Date getDT_FECHAMENTO() {
		return DT_FECHAMENTO;
	}

	public void setDT_FECHAMENTO(Date dT_FECHAMENTO) {
		DT_FECHAMENTO = dT_FECHAMENTO;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	
	
}
