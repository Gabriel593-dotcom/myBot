package com.example.bot.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bot.entities.Dicionario;
import com.example.bot.repositories.DicionarioRepository;
import com.example.bot.service.exceptions.ResourceAlreadyExistsException;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@Service
public class DicionarioService {

	@Autowired
	private DicionarioRepository repository;

	public List<Dicionario> findAll() {
		return repository.findAll();
	}

	public String findBySinonimo(String sinonimo) {
		// remove acentuação
		sinonimo = Normalizer.normalize(sinonimo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		// remove pontos, virgulas, exclamacões, interrogacões, etc...
		sinonimo = sinonimo.replaceAll("\\p{Punct}", "");

		sinonimo = sinonimo.toLowerCase().trim();
		String formatedMessage = "";
		List<Dicionario> dicio = findAll();

		for (int i = 0; i < sinonimo.split(" ").length; i++) {

			for (int l = 0; l < dicio.size(); l++) {

				System.out.println("sin: " + dicio.get(l).getSinonimos().split(",").length);

				for (int j = 0; j < dicio.get(l).getSinonimos().split(",").length; j++) {

					System.out.println("sinonimo: " + dicio.get(l).getSinonimos().split(",")[j] + " - " + "palavra: "
							+ sinonimo.split(" ")[i]);
					if (dicio.get(l).getSinonimos().split(",")[j].trim().equals(sinonimo.split(" ")[i].trim())) {
						formatedMessage += " " + dicio.get(l).getPalavra();
						j = dicio.get(l).getSinonimos().split(",").length;
						l = dicio.size() - 1;
					}
				}
			}
		}

		if(formatedMessage.isBlank() || formatedMessage.isEmpty()) {
			throw new ResourceNotFoundException("Desculpa, não te entendi.");
		}
		return formatedMessage.replaceAll("null", "").trim();
	}

	public Dicionario findById(Integer id) {

		Optional<Dicionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Sinonimo não foi encontrado."));
	}

	public void insert(Dicionario obj) {

		List<Dicionario> filtered = repository.findAll().stream().filter(x -> obj.equals(obj))
				.collect(Collectors.toList());
		if (filtered.isEmpty()) {
			repository.save(obj);
		} else {
			throw new ResourceAlreadyExistsException("O sinonimo que deseja incluir já existe na base de dados.");
		}
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

//	public Dicionario update(Integer id, Dicionario obj) {
//		Dicionario sinonimo = repository.getById(id);
//		setUpdates(sinonimo, obj);
//		
//		return repository.save(sinonimo);
//	}

//	private void setUpdates(Dicionario sinonimo, Dicionario obj) {
//		sinonimo.setSinonimo(obj.getSinonimo());
//		//sinonimo.setInteracao(obj.getInteracao());
//	}

}
