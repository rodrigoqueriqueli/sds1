package com.devsuperior.dspesquisa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dspesquisa.entities.Record;


//JpaRepository é uma classe generics é parametrizada com tipo (vai pedir o tipo da entidade, 
//e o ID da entidade

//RecordRepository objeto reponsavel por acessar dados de record..ela ja está pronta pra acessar os dados de games por herdar classe JpaRepository
@Repository
public interface GenreRepository extends JpaRepository<Record, Long>{

	
}
