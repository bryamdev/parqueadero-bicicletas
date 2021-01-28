package com.citcd.prueba.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citcd.prueba.models.dao.IPuestoDao;
import com.citcd.prueba.models.entity.Puesto;
import com.citcd.prueba.models.service.IPuestoService;

@Service
public class PuestoService implements IPuestoService {

	@Autowired
	private IPuestoDao puestoDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Puesto> findAll() {
		return (List<Puesto>) puestoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Puesto findById(Long id) {
		return puestoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public Puesto save(Puesto puesto) {
		return puestoDao.save(puesto);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		puestoDao.deleteById(id);
		
	}

}
