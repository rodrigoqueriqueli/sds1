package com.devsuperior.dspesquisa.repositories;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;


//JpaRepository é uma classe generics é parametrizada com tipo (vai pedir o tipo da entidade, 
//e o ID da entidade

//GameRepository objeto reponsavel por acessar dados de games..ela ja está pronta pra acessar os dados de games por herdar classe JpaRepository
@Repository
public interface RecordRepository extends JpaRepository<Game, Long>{

	Record save(Record entity);

	@Query("SELECT obj FROM Record obj WHERE "
			+ "(coalesce(:min, null) IS NULL OR obj.moment >= :min) AND "
			+ "(coalesce(:max, null) IS NULL OR obj.moment <= :max)")	
	Page<Record> findByMoments(Instant min, Instant max, Pageable pageable);

	
}
