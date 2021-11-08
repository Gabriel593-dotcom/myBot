package com.example.bot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bot.entities.Semantico;
import com.example.bot.service.SemanticoService;

@RestController
@RequestMapping(value = "/semanticos")
public class SemanticoController {

	@Autowired 
	private SemanticoService service;
	
	@GetMapping
	public ResponseEntity<List<Semantico>> findAll(){
		List<Semantico> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
}
