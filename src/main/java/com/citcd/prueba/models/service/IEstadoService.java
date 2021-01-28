package com.citcd.prueba.models.service;

import java.util.List;
import com.citcd.prueba.models.entity.Estado;

public interface IEstadoService {
	
	public List<Estado> findAll();
	 
	public Estado findByID(Long id);
	

}
