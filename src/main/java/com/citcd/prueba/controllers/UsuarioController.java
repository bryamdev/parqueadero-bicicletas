package com.citcd.prueba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citcd.prueba.models.dto.Response;
import com.citcd.prueba.models.entity.Usuario;
import com.citcd.prueba.models.service.IUsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		
		Response response = new Response(); 
		List<Usuario> usuarios = usuarioService.findAll();
		
		response.setIsOk(true);
		response.setResults(usuarios);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
		
		Response response = new Response();
		Usuario usuario = usuarioService.findById(id);
		
		if(usuario == null) {
			response.setIsOk(false);
			response.setError("No existe un usuario con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		response.setIsOk(true);
		response.setResult(usuario);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){
		
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
			Usuario usuarioNew = usuarioService.save(usuario);
			response = new Response(true, "Usuario creado exitosamente", usuarioNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			response.setIsOk(false);
			response.setMessage("Error al intentar crear el usuario");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody Usuario usuario, BindingResult result,
										@PathVariable Long id){
		
		Response response = new Response();		
		Usuario usuarioOld = usuarioService.findById(id);
		
		if(usuarioOld == null) {
			response.setIsOk(false);
			response.setError("No existe un usuario con id " + id);
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
			usuario.setId(id);
			usuario.setCreadoEn(usuarioOld.getCreadoEn());
			Usuario usuarioNew = usuarioService.save(usuario);
			
			response = new Response(true, "Usuario actualizado exitosamente", usuarioNew);
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}catch(Exception e) {
			response.setIsOk(false);
			response.setMessage("Error al intentar actualizar el usuario");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		
		Response response = new Response();		
		Usuario usuarioOld = usuarioService.findById(id);
		
		if(usuarioOld == null) {
			response.setIsOk(false);
			response.setError("No existe un usuario con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		try {
			usuarioService.deleteById(id);
			response.setIsOk(true);
			response.setMessage("Usuario eliminado exitosamente");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch (Exception e) {
			response.setIsOk(false);
			response.setMessage("Error al intentar actualizar el usuario");
			response.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
			
	

}
