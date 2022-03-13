package com.example.bot.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_interacao")
public class Interacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String pergunta;
	private String resposta;

	@ManyToOne
	@JoinColumn(name = "classe_id")
	private Classe classe;

	@JsonIgnore
	@OneToMany(mappedBy = "interacao")
	List<Semantico> semanticos = new ArrayList<>();

	public Interacao() {
	}

	public Interacao(Integer id, String pergunta, String resposta, Classe classe) {
		Id = id;
		this.pergunta = pergunta;
		this.resposta = resposta;
		this.classe = classe;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public List<Semantico> getSemanticos() {
		return semanticos;
	}

}
