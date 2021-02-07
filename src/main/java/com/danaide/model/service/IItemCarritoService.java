package com.danaide.model.service;

import java.util.List;

import com.danaide.model.ItemCarrito;
import com.danaide.model.ItemCarritoKey;

public interface IItemCarritoService {
	public void save(ItemCarrito itemCarrito);
	
	public List<ItemCarrito> findByCarritoIdCarrito(Long id);
}
