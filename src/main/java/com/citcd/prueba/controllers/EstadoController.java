package com.citcd.prueba.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		return ResponseEntity.status(HttpStatus.OK).body(estadoService.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
		
		Estado estado = estadoService.findByID(id);
		
		if(estado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Estado con id " + id);
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(estado);
	}
	

}
