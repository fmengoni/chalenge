package com.danaide.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danaide.model.Carrito;
import com.danaide.model.Usuario;

public interface ICarritoDao extends CrudRepository<Carrito, Long>{

	List<Carrito> findByUsuario(Usuario usuario);

	List<Carrito> findByUsuarioAndFechaGreaterThanAndFechaCierreIsNotNull(Usuario usuario, Date fecha);

	List<Carrito> findByUsuarioAndFechaLessThanAndFechaCierreIsNull(Usuario usuario, Date fecha);

}
