package com.example.bot.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.example.bot.entities.dto.InteracaoDTO;
import com.example.bot.service.InteracaoService;

@RestController
@RequestMapping(value = "/interacoes")
public class InteracaoController {

	@Autowired
	private InteracaoService service;

	@GetMapping
	public ResponseEntity<Page<InteracaoDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "pergunta") String orderBy) {
		Page<InteracaoDTO> pageList = service.findAll(page, linesPerPage, direction, orderBy)
				.map(obj -> new InteracaoDTO(obj));
		return ResponseEntity.ok().body(pageList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Interacao> findById(@PathVariable Integer id) {

		Interacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/findResposta")
	public ResponseEntity<Interacao> findResposta(@RequestParam("pergunta") String pergunta) {

		pergunta = URL.decodeParam(pergunta);
		Interacao interacao = service.findReposta(pergunta);
		return ResponseEntity.ok().body(interacao);
	}

	@PostMapping(value = "/saveInteracao")
	public ResponseEntity<Interacao> insert(@RequestBody Interacao obj) {

		service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

}
