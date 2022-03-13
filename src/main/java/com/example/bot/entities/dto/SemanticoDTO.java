package com.example.bot.entities.dto;

import java.io.Serializable;

import com.example.bot.entities.Semantico;

public class SemanticoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mensagemFormatada;
	private String pergunta;
	private String resposta;

	public SemanticoDTO() {
	}

	public SemanticoDTO(Semantico obj) {
		this.mensagemFormatada = obj.getMensagemFormatada();
		this.pergunta = obj.getInteracao().getPergunta();
		this.resposta = obj.getInteracao().getResposta();
	}

	public String getMensagemFormatada() {
		return mensagemFormatada;
	}

	public void setMensagemFormatada(String mensagemFormatada) {
		this.mensagemFormatada = mensagemFormatada;
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
}
