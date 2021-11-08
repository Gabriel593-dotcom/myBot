package com.example.bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bot.entities.Semantico;

public interface SemanticoRepository extends JpaRepository<Semantico, Long> {
	
	@Query(value="SELECT * FROM TB_SEMANTICO WHERE MENSAGEM_FORMATADA = :mesagemFormatada", nativeQuery = true)
	Semantico findByMensagemFormatada(@Param("mesagemFormatada") String mensagem);
}
