package com.citcd.prueba.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citcd.prueba.models.dao.IEstadoDao;
import com.citcd.prueba.models.entity.Estado;
import com.citcd.prueba.models.service.IEstadoService;


@Service
public class EstadoService implements IEstadoService{

	@Autowired
	private IEstadoDao estadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Estado> findAll() {
		return (List<Estado>) estadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Estado findByID(Long id) {
		return estadoDao.findById(id).orElse(null);
	}
	
	@Override
	public Estado cambiarEstado(Estado estado) {
		
		if(estado.getId() == 1) {
			estado.setId(2L);
			estado.setNombre("Ocupado");
		}
		
		if(estado.getId() == 2) {
			estado.setId(1L);
			estado.setNombre("Libre");
		}
		
		return estado;
	}

}
