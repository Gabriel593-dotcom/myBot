package com.example.bot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Dicionario;
import com.example.bot.repositories.DicionarioRepository;
import com.example.bot.service.exceptions.ResourceAlreadyExistsException;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@Service
public class DicionarioService {
	
	@Autowired
	private DicionarioRepository repository;
	
	public List<Dicionario> findAll(){
		return repository.findAll();
	}
	
	public Dicionario findBySinonimo(String sinonimo, int posicao) {
		Dicionario result = repository.findBySinonimo(sinonimo, posicao);
		if(result == null) {
			result = new Dicionario();
		}
		
		return result;
	} 
	
	public Dicionario findById(Long id) {
		
		Optional<Dicionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Sinonimo não foi encontrado."));
	}
	
	public void insert(Dicionario obj) {
		
		List<Dicionario> filtered = repository.findAll().stream().filter(x -> obj.equals(obj)).collect(Collectors.toList());
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
	
//	public Dicionario update(Long id, Dicionario obj) {
//		Dicionario sinonimo = repository.getById(id);
//		setUpdates(sinonimo, obj);
//		
//		return repository.save(sinonimo);
//	}
	
//	private void setUpdates(Dicionario sinonimo, Dicionario obj) {
//		sinonimo.setSinonimo(obj.getSinonimo());
//		//sinonimo.setInteracao(obj.getInteracao());
//	}
	
}
