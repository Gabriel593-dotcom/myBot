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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.bot.entities.Dicionario;
import com.example.bot.entities.Semantico;
import com.example.bot.service.DicionarioService;
import com.example.bot.service.SemanticoService;

@RestController
@RequestMapping(value = "/dicionarios")
public class DicionarioController {

	@Autowired
	private DicionarioService service;
	
	@Autowired private SemanticoService semanticoService;

	@GetMapping
	public ResponseEntity<List<Dicionario>> findAll() {

		List<Dicionario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/findSinonimo")
	public ResponseEntity<String> findBySinonimo(@RequestParam("sinonimo") String sinonimo) {
		// remove acentuação
		sinonimo = Normalizer.normalize(sinonimo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

		// remove pontos, virgulas, exclamacões, interrogacões, etc...
		sinonimo = sinonimo.replaceAll("\\p{Punct}", "");
		
		sinonimo = sinonimo.toLowerCase().trim();
		String formatedMessage = "";		
		List<Dicionario> dicio = service.findAll();
		
		for(int i = 0; i<sinonimo.split(" ").length; i++) {
			for(Dicionario d : dicio) {
				System.out.println("sin: " + d.getSinonimos().split(",").length);
				for(int j = 0; j<d.getSinonimos().split(",").length; j++) {
					System.out.println("sinonimos: " + d.getSinonimos().split(",")[j] + " - " + d.getSinonimos().split(",")[j].length() + " - position: " + j);
					System.out.println("palavra: " + sinonimo.split(" ")[i] + " - " + sinonimo.split(" ")[i].length() + " - position: " + i);
					
					if(d.getSinonimos().split(",")[j].equals(sinonimo.split(" ")[i])) {
						formatedMessage += " " + d.getPalavra();
					}
				}
				System.out.println("----------------------------");
			}
		}
		
//		for(int i = 0; i<sinonimo.split(" ").length;i++) {
//			System.out.println(i);
//		}
		
		formatedMessage = formatedMessage.replaceAll("null", "").trim();
		
		System.out.println("mensagem formatada: " + formatedMessage);
		
		Semantico resp = semanticoService.findByMensagemFormatada(formatedMessage);

		return ResponseEntity.ok().body(resp.getInteracao().getResposta());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Dicionario> findById(@PathVariable Long id){
		Dicionario obj = service.findById(id);return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value="/saveSinonimo")
	public ResponseEntity<Dicionario> insert(@RequestBody Dicionario sinonimo) {
		
		service.insert(sinonimo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sinonimo.getId()).toUri();
		return ResponseEntity.created(uri).body(sinonimo);
	}

	@DeleteMapping(value="/deleteSinonimo")
	public ResponseEntity<Void> delete(@RequestParam("sinonimo_id") long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

//	@PutMapping(value="/updateSinonimo")
//	public ResponseEntity<Dicionario> update(@RequestParam("sinonimo_id") Long id, @RequestBody Dicionario obj){
//		service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
