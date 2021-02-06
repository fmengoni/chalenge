package com.danaide.model.service;

import java.util.List;

import com.danaide.model.Producto;

public interface IProductoService {
	public Producto findById(Long id);
	
	public List<Producto> getProductos();
	
	public List<Producto> findByDescripcionLike(String descripcion);
}
