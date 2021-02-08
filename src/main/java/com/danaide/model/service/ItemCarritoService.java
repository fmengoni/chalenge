package com.danaide.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaide.model.CarritoItem;
import com.danaide.model.dao.IItemCarritoDao;

@Service
public class ItemCarritoService implements IItemCarritoService {
	@Autowired
	private IItemCarritoDao itemCarritoDao;
	
	@Override
	public void save(CarritoItem itemCarrito) {
		itemCarritoDao.save(itemCarrito);
	}

	@Override
	public List<CarritoItem> findByCarritoIdCarrito(Long id) {
		return itemCarritoDao.findByCarritoIdCarrito(id);
	}

	@Override
	public void deleteItem(Long idItem) throws Exception {
		CarritoItem carritoItem = findById(idItem);
		if(carritoItem != null) {
			itemCarritoDao.delete(carritoItem);
		} else {
			throw new Exception("El item con id " + idItem + " no existe");
		}
	}

	@Override
	public CarritoItem findById(Long idItemCarrito) {
		Optional<CarritoItem> oCarritoItem = itemCarritoDao.findById(idItemCarrito);
		return oCarritoItem.isPresent() ? oCarritoItem.get() : null;
	}

}
