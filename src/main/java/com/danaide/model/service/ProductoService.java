package com.danaide.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaide.model.Producto;
import com.danaide.model.dao.IProductoDao;

@Service
public class ProductoService implements IProductoService {
	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public List<Producto> getProductos() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	public List<Producto> findByDescripcionLike(String descripcion) {
		return productoDao.findByDescripcionLike(descripcion);
	}

	@Override
	public Producto findById(Long id) {
		Optional<Producto> productoOptional = productoDao.findById(id);
		return productoOptional.isPresent() ? productoOptional.get() : null; 
	}

}
