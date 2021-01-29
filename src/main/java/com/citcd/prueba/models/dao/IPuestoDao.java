package com.citcd.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.citcd.prueba.models.entity.Puesto;

public interface IPuestoDao extends CrudRepository<Puesto, Long>{
	
	public Puesto findByUbicacion(String ubicacion);
	

}
