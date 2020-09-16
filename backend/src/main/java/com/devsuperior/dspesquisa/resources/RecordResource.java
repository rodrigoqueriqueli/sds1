package com.devsuperior.dspesquisa.resources;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

//para implementar essa classe como um controlador Rest (que vai ser um webservice q 
//respondera pelas requisicoes)

@RestController
@RequestMapping(value = "/records") // passando a rota principal do recurso ( o rest ira responder por esse recurso)
public class RecordResource {

	// injetar dependencia do GameService
	@Autowired
	private RecordService service;

	// implmentando método de teste pra ver se consigo trazer infos do bd e mostrar
	// pro user
	// metodo pra implementar um endpoint (uma rota e recuperar os
	// games)--Webservice REST implementado...

	@PostMapping  //implementar pra salvar
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) {
		//inseri no BD chamando o serviço
		RecordDTO newDTO = service.insert(dto);
		return ResponseEntity.ok().body(newDTO);
	}

	@GetMapping  //avisando que é uma requisição get
	public ResponseEntity<Page<RecordDTO>> findAdll(
			//metodo recebendo todos esses parametros para configurar request
			@RequestParam(value = "min", defaultValue = "") String min,
			@RequestParam(value = "max", defaultValue = "") String max,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage, //está zerado pra poder mostrar todos
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction){
		
		//pra converter a data que ta em formado de String min pro Instant
		Instant minDate = ("".equals(min)) ? null : Instant.parse(min); 
		Instant maxDate = ("".equals(max)) ? null : Instant.parse(max); 
		
		if (linesPerPage == 0) {
			linesPerPage = Integer.MAX_VALUE; //maximo valor inteiro possivel
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		//essa linha que vai buscar no BD e retorna pra essa lista de registros
		Page<RecordDTO> list = service.findByMoments(minDate, maxDate, pageRequest); 
		return ResponseEntity.ok().body(list); 
	}
}
