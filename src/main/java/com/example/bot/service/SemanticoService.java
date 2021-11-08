package com.example.bot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Semantico;
import com.example.bot.repositories.SemanticoRepository;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@Service
public class SemanticoService {
	
	@Autowired
	private SemanticoRepository repository;
	
	public List<Semantico> findAll(){
		return repository.findAll();
	}
	
	public Semantico findByMensagemFormatada(String mensagem) {
		Semantico obj = repository.findByMensagemFormatada(mensagem);  
		if(obj != null) {
			return obj;
		}
		else {
			throw new ResourceNotFoundException("Desculpe...nã te entendi. Tente com outras palavras por gentileza.");
		}
	}
}
