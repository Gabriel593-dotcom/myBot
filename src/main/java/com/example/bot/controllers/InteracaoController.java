package com.example.bot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.bot.controllers.util.URL;
import com.example.bot.entities.Interacao;
import com.example.bot.service.InteracaoService;

@RestController
@RequestMapping(value="/interacoes")
public class InteracaoController {
	
	@Autowired
	private InteracaoService service;
	
	@GetMapping
	public ResponseEntity<List<Interacao>> findAll(){
		List<Interacao> list = service.findAll();	
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Interacao> findById(@PathVariable Long id){
		
		Interacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/findResposta")
	public ResponseEntity<Interacao> findResposta(@RequestParam("pergunta") String pergunta){
		
		pergunta = URL.decodeParam(pergunta);
		Interacao interacao = service.findReposta(pergunta);
		return ResponseEntity.ok().body(interacao);
	}
	
	@PostMapping(value="/saveInteracao")
	public ResponseEntity<Interacao> insert(@RequestBody Interacao obj){
		
		service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
}
