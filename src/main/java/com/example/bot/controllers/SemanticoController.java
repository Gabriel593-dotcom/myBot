package com.example.bot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bot.entities.Semantico;
import com.example.bot.entities.dto.SemanticoDTO;
import com.example.bot.service.SemanticoService;

@RestController
@RequestMapping(value = "/semanticos")
public class SemanticoController {

	@Autowired
	private SemanticoService service;

	@GetMapping
	public ResponseEntity<Page<SemanticoDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "mensagemFormatada") String orderBy) {
		
		Page<SemanticoDTO> pageList = service.findAll(page, linesPerPage, direction, orderBy).map(obj -> new SemanticoDTO(obj));
		return ResponseEntity.ok().body(pageList);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Semantico> findById(@PathVariable Integer id){
		Semantico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
