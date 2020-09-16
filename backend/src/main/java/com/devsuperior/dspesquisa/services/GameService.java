package com.devsuperior.dspesquisa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.GameDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.repositories.GameRepository;

@Service //ou o @Component q tem o mesmo papel
public class GameService { //servico pra trabalhar com games
	
	//criando dependencia para o GameRepository
	@Autowired //pra injetar a dependencia automaticamente
	private GameRepository repository;

	//metodo pra retornar os games
	//pra garantir que a parte de banco vai ser feita no service uso essa notacao...no caso só de busca, por isso read only
	@Transactional(readOnly = true)
	public List<GameDTO> findAll(){ //GameDTO, não mais a entidade
		//repository é o objeto do repository
		List<Game> list = repository.findAll(); //buscar no BD todos os games e guardar numa lista, e depois transformar lista de games e lista de gamesDTO
		//o stream transforma lista numa stream que aceita funcoes de alta ordem e funcoes lambda...map pra transformar cada objeto
		return list.stream().map(x -> new GameDTO(x)).collect(Collectors.toList());  //o to list serve para transformar de volta numa lista
	}
	
}
