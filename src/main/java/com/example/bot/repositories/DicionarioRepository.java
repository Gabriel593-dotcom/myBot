package com.example.bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bot.entities.Dicionario;
import com.example.bot.entities.Dicionario;

public interface DicionarioRepository extends JpaRepository<Dicionario, Long>{

//	@Query(value = "select * from tb_dicionario where substring_index(sinonimos, ',' ,:posicao) like %:sinonimo% limit 1", nativeQuery = true)
//	Dicionario findBySinonimo(@Param("sinonimo") String sinonimo, @Param("posicao") int posicao);

}
