package com.danaide.model.service;

import java.util.List;

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

}
