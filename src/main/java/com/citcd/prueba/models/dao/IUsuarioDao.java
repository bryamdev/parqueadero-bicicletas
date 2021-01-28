package com.citcd.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.citcd.prueba.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

}
