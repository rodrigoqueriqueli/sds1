package com.devsuperior.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;

@Service // ou o @Component q tem o mesmo papel
public class RecordService { // servico pra trabalhar com games

	// criando dependencia para o GameRepository
	// RecordRepository dependencia basica
	@Autowired
	private RecordRepository repository;
	
	@Autowired
	private GameRepository gameRepository;

	// implementar metodo pra salvar..o novo record baseado no recordDTO
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		//instanciando record que sera a entidade
		Record entity = new Record(); 
		
		//setando valores
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now()); //pegando momento atual
		
		
		//getOne vai instanciar um objeto do tipo relacionado q precisa
		Game game = gameRepository.getOne(dto.getGameId());
		//tenho que passar o jogo inteiro, por isso usei o autowired ali em cima
		//setando objeto relacionado
		entity.setGame(game); 
		
		/*salvando*/
		//passando entity como argumento
		//variavel entity recebe resultado do salvamento--referencia atualizada pro objeto salvo
		entity = repository.save(entity); 
		return new RecordDTO(entity);
	
	}

	//readOnly pois é só uma operação de busca
	@Transactional(readOnly = true)	
	public Page<RecordDTO> findByMoments(Instant minDate, Instant maxDate, PageRequest pageRequest) {
		return repository.findByMoments(minDate, maxDate, pageRequest).map(x -> new RecordDTO(x));
	}

}
