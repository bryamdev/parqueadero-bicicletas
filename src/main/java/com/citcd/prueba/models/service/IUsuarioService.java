package com.citcd.prueba.models.service;

import java.util.List;
import com.citcd.prueba.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void deleteById(Long id);
	
	public Usuario findByCodigo(String codigo);
	
}
