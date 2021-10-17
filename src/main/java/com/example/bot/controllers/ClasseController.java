package com.example.bot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bot.entities.Classe;
import com.example.bot.service.ClasseService;

@RestController
@RequestMapping(value="/classes")
public class ClasseController {

	@Autowired
	private ClasseService service;
	
	@GetMapping
	public ResponseEntity<List<Classe>> findAll(){
		List<Classe> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
}
