package com.example.bot.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_semantico")
public class Semantico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mensagemFormatada;
	
	@ManyToOne
	@JoinColumn(name = "interacao_id")
	private Interacao interacao;

	public Semantico() {
	}

	public Semantico(Integer id, String mensagemFormatada, Interacao interacao) {
		this.id = id;
		this.mensagemFormatada = mensagemFormatada;
		this.interacao = interacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagemFormatada() {
		return mensagemFormatada;
	}

	public void setMensagemFormatada(String mensagemFormatada) {
		this.mensagemFormatada = mensagemFormatada;
	}

	public Interacao getInteracao() {
		return interacao;
	}

	public void setInteracao(Interacao interacao) {
		this.interacao = interacao;
	}

}
