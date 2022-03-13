package com.example.bot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Semantico;
import com.example.bot.repositories.SemanticoRepository;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@Service
public class SemanticoService {
	
	@Autowired
	private SemanticoRepository repository;
	
	public Page<Semantico> findAll(Integer page, Integer size, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
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
	
	public Semantico findById(Integer id) {
		Optional<Semantico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Mensagem formatada não encontrada."));
	}
}
