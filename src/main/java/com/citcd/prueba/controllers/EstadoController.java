package com.citcd.prueba.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citcd.prueba.models.dto.Response;
import com.citcd.prueba.models.entity.Estado;
import com.citcd.prueba.models.service.IEstadoService;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadoController {
	
	@Autowired
	private IEstadoService estadoService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> findAll(){
		
		log.info("Si entró al método!");
		
		Response response = new Response();
		List<Estado> estados = estadoService.findAll();
		
		response.setIsOk(true);
		response.setResults(estados);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
		
		Response response = new Response();
		Estado estado = estadoService.findByID(id);
		
		if(estado == null) {
			response.setIsOk(false);
			response.setError("No existe un estado con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		response.setIsOk(true);
		response.setResult(estado);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	

}
