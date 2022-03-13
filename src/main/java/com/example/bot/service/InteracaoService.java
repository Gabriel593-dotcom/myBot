package com.example.bot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Interacao;
import com.example.bot.entities.Semantico;
import com.example.bot.repositories.InteracaoRepository;
import com.example.bot.repositories.SemanticoRepository;
import com.example.bot.service.exceptions.ResourceAlreadyExistsException;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@Service
public class InteracaoService {

	@Autowired
	private InteracaoRepository repository;

	@Autowired
	private SemanticoRepository semanticoRepository;

	@Autowired
	private DicionarioService dicioService;

	public Page<Interacao> findAll(Integer page, Integer size, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Interacao findById(Integer id) {

		Optional<Interacao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Interação não encontrada."));
	}

	public Interacao findReposta(String pergunta) {
		String mensagemFormatada = dicioService.findBySinonimo(pergunta);
		Semantico semantico = semanticoRepository.findByMensagemFormatada(mensagemFormatada);
		return semantico.getInteracao();
	}

	public void insert(Interacao obj) {

		List<Interacao> filteredObj = repository.findAll().stream().filter(i -> i.equals(obj))
				.collect(Collectors.toList());

		if (filteredObj.isEmpty()) {
			repository.save(obj);
		} else {
			throw new ResourceAlreadyExistsException("A interação já está registrada na base de dados.");
		}
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
