package com.danaide.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danaide.model.ItemCarrito;
import com.danaide.model.ItemCarritoKey;

public interface IItemCarritoDao extends CrudRepository<ItemCarrito, ItemCarritoKey>{
	public List<ItemCarrito> findByCarritoIdCarrito(Long idCarrito);
}
