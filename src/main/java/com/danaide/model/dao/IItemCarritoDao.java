package com.danaide.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danaide.model.CarritoItem;

public interface IItemCarritoDao extends CrudRepository<CarritoItem, Long>{
	public List<CarritoItem> findByCarritoIdCarrito(Long idCarrito);
}
