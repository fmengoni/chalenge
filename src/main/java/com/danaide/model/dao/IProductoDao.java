package com.danaide.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danaide.model.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	List<Producto> findByDescripcionLike(String descripcion);
}
