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
@Table(name = "tb_dicionario")
public class Dicionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String palavra;
	private String sinonimos;

	public Dicionario() {
	}

	public Dicionario(Long id, String palavra, String sinonimos) {
		this.id = id;
		this.palavra = palavra;
		this.sinonimos = sinonimos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getSinonimos() {
		return sinonimos;
	}

	public void setSinonimos(String sinonimos) {
		this.sinonimos = sinonimos;
	}
}
