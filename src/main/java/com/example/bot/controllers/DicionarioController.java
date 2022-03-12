package com.example.bot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.bot.entities.Dicionario;
import com.example.bot.entities.Interacao;
import com.example.bot.entities.Semantico;
import com.example.bot.service.DicionarioService;
import com.example.bot.service.SemanticoService;

@RestController
@RequestMapping(value = "/dicionarios")
public class DicionarioController {

	@Autowired
	private DicionarioService service;

	@Autowired
	private SemanticoService semanticoService;

	@GetMapping
	public ResponseEntity<List<Dicionario>> findAll() {

		List<Dicionario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Dicionario> findById(@PathVariable Long id) {
		Dicionario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/saveSinonimo")
	public ResponseEntity<Dicionario> insert(@RequestBody Dicionario sinonimo) {

		service.insert(sinonimo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sinonimo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(sinonimo);
	}

	@DeleteMapping(value = "/deleteSinonimo")
	public ResponseEntity<Void> delete(@RequestParam("sinonimo_id") long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

//	@PutMapping(value="/updateSinonimo")
//	public ResponseEntity<Dicionario> update(@RequestParam("sinonimo_id") Long id, @RequestBody Dicionario obj){
//		service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
