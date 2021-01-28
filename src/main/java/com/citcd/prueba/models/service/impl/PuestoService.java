package com.citcd.prueba.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citcd.prueba.models.dao.IPuestoDao;
import com.citcd.prueba.models.entity.Puesto;
import com.citcd.prueba.models.service.IPuestoService;

@Service
public class PuestoService implements IPuestoService {

	@Autowired
	private IPuestoDao puestoDao;
	
	
	@Override
	public List<Puesto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puesto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puesto save(Puesto puesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
