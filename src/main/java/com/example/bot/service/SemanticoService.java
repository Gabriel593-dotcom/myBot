package com.example.bot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Semantico;
import com.example.bot.repositories.SemanticoRepository;
import com.example.bot.service.exceptions.ResourceAlreadyExistsException;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@Service
public class SemanticoService {
	
	@Autowired
	private SemanticoRepository repository;
	
	public List<Semantico> findAll(){
		return repository.findAll();
	}
	
	public Semantico findBySinonimo(String sinonimo) {
		Semantico result = repository.findBySinonimo(sinonimo);
		if(result == null) {
			System.out.println("Nada encontrado"); 
			throw new ResourceNotFoundException("Desculpe...não consegui te entender. Poderia escrever de outra forma?");
		}
		
		return result;
	} 
	
	public Semantico findById(Long id) {
		
		Optional<Semantico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Sinonimo não foi encontrado."));
	}
	
	public void insert(Semantico obj) {
		
		List<Semantico> filtered = repository.findAll().stream().filter(x -> obj.equals(obj)).collect(Collectors.toList());
		if(filtered.isEmpty()) {
			repository.save(obj);
		}
		else {
			throw new ResourceAlreadyExistsException("O sinonimo que deseja incluir já existe na base de dados.");
		}
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Semantico update(Long id, Semantico obj) {
		Semantico sinonimo = repository.getById(id);
		setUpdates(sinonimo, obj);
		
		return repository.save(sinonimo);
	}
	
	private void setUpdates(Semantico sinonimo, Semantico obj) {
		sinonimo.setSinonimo(obj.getSinonimo());
		//sinonimo.setInteracao(obj.getInteracao());
	}
	
}
