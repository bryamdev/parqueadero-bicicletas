package com.citcd.prueba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citcd.prueba.models.entity.Usuario;
import com.citcd.prueba.models.service.IUsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
		
		Usuario usuario = usuarioService.findById(id);
		
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un usuario con id " + id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map( field -> {
						return "El campo '" + field.getField() + "' : " + field.getDefaultMessage();
					})
					.collect(Collectors.toList());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		try {
			Usuario usuarioNew = usuarioService.save(usuario);
			return ResponseEntity.status(HttpStatus.OK).body(usuarioNew);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	
	

}
