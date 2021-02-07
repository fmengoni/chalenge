package com.danaide.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaide.model.ItemCarrito;
import com.danaide.model.dao.IItemCarritoDao;

@Service
public class ItemCarritoService implements IItemCarritoService {
	@Autowired
	private IItemCarritoDao itemCarritoDao;
	
	@Override
	public void save(ItemCarrito itemCarrito) {
		itemCarritoDao.save(itemCarrito);
	}

	@Override
	public List<ItemCarrito> findByCarritoIdCarrito(Long id) {
		return itemCarritoDao.findByCarritoIdCarrito(id);
	}

}
