package com.devsuperior.dspesquisa.dto;

import java.io.Serializable;
import java.time.Instant;

import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.entities.enums.Platform;

public class RecordDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	// fazendo outro RecordDTO, pois quando vai retornar na API o resultado da
	// insercao..quem esta consumindo a API ver se de fato inseriu

	private Long id; // id do record inserido
	private Instant moment;
	private String name;
	private Integer age;
	private String gameTitle;
	private Platform gamePlatform;
	private String genreName;

	public RecordDTO() {
	}

	// construtor recebendo uma entidade, recebendo uma entidade q esta sendo
	// monitorada
	//construtor que passa dar o record e ele constroi o DTO
	public RecordDTO(Record entity) { 
		id = entity.getId();
		moment = entity.getMoment();
		name = entity.getName();
		age = entity.getAge();
		gameTitle = entity.getGame().getTitle(); //peguei o titulo do game associado a esse registro de pesquisa
		gamePlatform = entity.getGame().getPlatform();
		genreName = entity.getGame().getGenre().getName(); //getName pra acessar o nome do genero
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public Platform getGamePlatform() {
		return gamePlatform;
	}

	public void setGamePlatform(Platform gamePlatform) {
		this.gamePlatform = gamePlatform;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	
}
