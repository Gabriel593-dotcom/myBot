package com.example.bot.controllers;

import java.net.URI;
import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.bot.entities.Semantico;
import com.example.bot.service.SemanticoService;

@RestController
@RequestMapping(value="/semanticos")
public class SemanticoController {

	@Autowired
	private SemanticoService service;
	
	@GetMapping
	public ResponseEntity<List<Semantico>> findAll(){
		
		List<Semantico> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value="/findSinonimo")
	public ResponseEntity<String > findBySinonimo(@RequestParam("sinonimo") String sinonimo){
		//remove acentuação
		sinonimo = Normalizer.normalize(sinonimo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		
		
		// remove pontos, virgulas, exclamacões, interrogacões, etc...
		//sinonimo = sinonimo.replaceAll("\\p{Punct}", ""); 
		
		
		sinonimo = sinonimo.toLowerCase().trim();
		
		System.out.println(sinonimo);
		
		Semantico obj = service.findBySinonimo(sinonimo);
		return ResponseEntity.ok().body(obj.getInteracao().getResposta());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Semantico> findById(@PathVariable Long id){
		Semantico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/saveSinonimo")
	public ResponseEntity<Semantico> insert(@RequestBody Semantico sinonimo) {
		
		service.insert(sinonimo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sinonimo.getId()).toUri();
		return ResponseEntity.created(uri).body(sinonimo);
	}
	
	@DeleteMapping(value="/deleteSinonimo")
	public ResponseEntity<Void> delete(@RequestParam("sinonimo_id") long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/updateSinonimo")
	public ResponseEntity<Semantico> update(@RequestParam("sinonimo_id") Long id, @RequestBody Semantico obj){
		service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
