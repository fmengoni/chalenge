package com.danaide.model.service;

import java.util.List;

import com.danaide.model.CarritoItem;

public interface IItemCarritoService {
	public CarritoItem findById(Long idItemCarrito);
	
	public void save(CarritoItem itemCarrito);
	
	public List<CarritoItem> findByCarritoIdCarrito(Long id);

	public void deleteItem(Long idItem) throws Exception;
}
