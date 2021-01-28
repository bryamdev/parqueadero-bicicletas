package com.citcd.prueba.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citcd.prueba.models.dao.IUsuarioDao;
import com.citcd.prueba.models.entity.Usuario;
import com.citcd.prueba.models.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll() ;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		usuarioDao.deleteById(id);
		
	}

}
