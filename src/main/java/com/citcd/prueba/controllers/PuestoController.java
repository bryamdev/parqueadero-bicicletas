package com.citcd.prueba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citcd.prueba.models.dto.Response;
import com.citcd.prueba.models.entity.Puesto;
import com.citcd.prueba.models.service.IPuestoService;

@CrossOrigin(origins = {"http://localhost:4200", "**", "*"})
@RestController
@RequestMapping("/api/v1/puestos")
public class PuestoController {
	
	
	@Autowired
	private IPuestoService puestoService;
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		
		Response response = new Response();
		List<Puesto> puestos = puestoService.findAll();
		
		response.setIsOk(true);
		response.setResults(puestos);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
		
		Response response = new Response();
		Puesto puesto = puestoService.findById(id);
		
		if(puesto == null) {
			response.setIsOk(false);
			response.setError("No existe un puesto con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		response.setIsOk(true);
		response.setResult(puesto);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crear(@Valid @RequestBody Puesto puesto, BindingResult result){
		
		Response response = new Response();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map( field -> {
						return "El campo '" + field.getField() + "' : " + field.getDefaultMessage();
					})
					.collect(Collectors.toList());
			
			response.setIsOk(false);
			response.setError("Error en la validacion de campos");
			response.setResults(errors);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		try {
			Puesto puestoNew = puestoService.save(puesto);
			response = new Response(true, "Puesto creado exitosamente", puestoNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setIsOk(false);
			response.setMessage("Error al intentar crear el puesto");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody Puesto puesto, BindingResult result,
										@PathVariable Long id){
		
		Response response = new Response();		
		Puesto puestoOld = puestoService.findById(id);
		
		if(puestoOld == null) {
			response.setIsOk(false);
			response.setError("No existe un puesto con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map( field -> {
						return "El campo '" + field.getField() + "' : " + field.getDefaultMessage();
					})
					.collect(Collectors.toList());
			
			response.setIsOk(false);
			response.setError("Error en la validacion de campos");
			response.setResults(errors);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		try {
			puesto.setId(id);
			Puesto puestoNew = puestoService.save(puesto);
			
			response = new Response(true, "Puesto actualizado exitosamente", puestoNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}catch(Exception e) {
			response.setIsOk(false);
			response.setMessage("Error al intentar actualizar el puesto");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	@PutMapping("/desocupar/{id}")
	public ResponseEntity<?> desocuparPuesto(@PathVariable Long id){
		
		Response response = new Response();
		Puesto puesto = puestoService.findById(id);
		
		if(puesto == null) {
			response.setIsOk(false);
			response.setError("No existe un puesto con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		try {
			Puesto puestoNew = puestoService.desocupar(puesto);
			response = new Response(true, "Puesto desocupado correctamente", puestoNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setIsOk(false);
			response.setMessage("Error al intentar desocupar el puesto");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		
	}
		
	

}
