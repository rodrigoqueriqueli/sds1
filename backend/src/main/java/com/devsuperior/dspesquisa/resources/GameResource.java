package com.devsuperior.dspesquisa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.GameDTO;
import com.devsuperior.dspesquisa.services.GameService;

//para implementar essa classe como um controlador Rest (que vai ser um webservice q 
//respondera pelas requisicoes)

@RestController
@RequestMapping(value = "/games") //passando a rota principal do recurso ( o rest ira responder por esse recurso)
public class GameResource {

	/*Ma pratica (só fiz isso pra efeito de teste)
	//(PRA EFEITO de teste -- declarando uma dependencia(uma instancia) pro GameRepository (reponsavel por acessar os dados de games no banco)
	@Autowired //notacao que faz injecao de dependencia (sem precisar fazer manual) //ela me entrega uma instancia do GameRepository automaticamente
	private GameRepository gameRepository;
	 */

	//injetar dependencia do GameService
	@Autowired
	private GameService service;
	
	//implmentando método de teste pra ver se consigo trazer infos do bd e mostrar pro user
	//metodo pra implementar um endpoint (uma rota e recuperar os games)--Webservice REST implementado...
	
	@GetMapping  //avisando que é uma requisição get
	public ResponseEntity<List<GameDTO>> findAdll(){
		
		List<GameDTO> list = service.findAll();//lista chamada a partir do gameRepository..findAll pra buscar todos games no BD
		return ResponseEntity.ok().body(list); //construir um objeto com resposta que deu certo a requisicao...body pra dizer o que tem no corpo da resposta
	}
}
