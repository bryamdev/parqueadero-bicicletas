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
		// TODO Auto-generated method stub
		return (List<Estado>) estadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Estado findByID(Long id) {
		// TODO Auto-generated method stub
		return estadoDao.findById(id).orElse(null);
	}

}
