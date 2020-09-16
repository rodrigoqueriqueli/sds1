package com.devsuperior.dspesquisa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

//para implementar essa classe como um controlador Rest (que vai ser um webservice q 
//respondera pelas requisicoes)

@RestController
@RequestMapping(value = "/records") //passando a rota principal do recurso ( o rest ira responder por esse recurso)
public class RecordResource {

	//injetar dependencia do GameService
	@Autowired
	private RecordService service;
	
	//implmentando método de teste pra ver se consigo trazer infos do bd e mostrar pro user
	//metodo pra implementar um endpoint (uma rota e recuperar os games)--Webservice REST implementado...
	
	@PostMapping  //implementar pra salvar
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) {
		//inseri no BD chamando o serviço
		RecordDTO newDTO = service.insert(dto);
		return ResponseEntity.ok().body(newDTO);
	}
}
