package com.devsuperior.dspesquisa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity; //JPA
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//Pra falar que essa classe vai ser mapeada pro banco de dados uso o @Entity
//Objetos do tipo genero serão monitorados pelo JPA, quando eu salvar um obj do tipo genero, estarei salvando um registro na tabela do BD
@Entity
@Table (name = "tb_genre") //no bd o nome dessa tabela sera tb_genre
public class Genre implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoimplementado
	private Long id;
	
	private String name;
	
	//associacao de mao dupla, meu genero tb esta associado com uma lista de games
	//ja instanciando a lista pra ficar disponivel pra uso..Lista é uma interface(por isso posso instanciar, 
	//tenho que instanciar classe que implementa interface)
	@OneToMany (mappedBy = "genre") //mapeamento pra avisar o JPA sobre essa lista de games dentro de genre
	
	private List<Game> games = new ArrayList<>();
	
	
	public Genre() {
	}

	public Genre(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Game> getGames() {
		return games;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
