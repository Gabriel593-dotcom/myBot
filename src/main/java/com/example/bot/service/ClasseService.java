package com.example.bot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Classe;
import com.example.bot.repositories.ClasseRepository;

@Service
public class ClasseService {

	@Autowired
	private ClasseRepository repository;

	public List<Classe> findAll(){
		return repository.findAll();
	}
}
