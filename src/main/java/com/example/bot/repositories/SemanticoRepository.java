package com.example.bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bot.entities.Semantico;

public interface SemanticoRepository extends JpaRepository<Semantico, Long>{

//	@Query(value = "select * from tb_semantico where ", nativeQuery = true)
//	Semantico findPerguntaByContent();

	Semantico findBySinonimo(String sinonimo);
}
