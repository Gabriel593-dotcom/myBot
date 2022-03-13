package com.example.bot.entities.dto;

import java.io.Serializable;

import com.example.bot.entities.Interacao;

public class InteracaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String pergunta;
	private String resposta;

	public InteracaoDTO() {
	}

	public InteracaoDTO(Interacao obj) {
		this.pergunta = obj.getPergunta();
		this.resposta = obj.getResposta();
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
