package com.citcd.prueba.models.service;

import java.util.List;
import com.citcd.prueba.models.entity.Puesto;

public interface IPuestoService {
	
	public List<Puesto> findAll();
	
	public Puesto findById(Long id);
	
	public Puesto save(Puesto puesto);
	
	public void deleteById(Long id);
	
	public Puesto desocupar(Puesto puesto);
		
	
}
