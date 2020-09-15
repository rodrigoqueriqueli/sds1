package com.devsuperior.dspesquisa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dspesquisa.entities.Game;


//JpaRepository é uma classe generics é parametrizada com tipo (vai pedir o tipo da entidade, 
//e o ID da entidade

//GameRepository objeto reponsavel por acessar dados de games..ela ja está pronta pra acessar os dados de games por herdar classe JpaRepository
public interface GameRepository extends JpaRepository<Game, Long>{

	
}
