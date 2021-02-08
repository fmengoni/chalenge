package com.danaide.model.service;

import java.util.Date;
import java.util.List;

import com.danaide.model.Carrito;
import com.danaide.model.CarritoItem;
import com.danaide.model.Usuario;

public interface ICarritoService {
	public Carrito findById(Long idCarrito);
	
	public List<Carrito> getCarritos();
	
	public void save(Carrito carrito);
	
	public void cerrarCarrito(Carrito carrito);
	
	public List<Carrito> findByUsuario(Usuario usuario);
	
	public List<Carrito> findByUsuarioAndFechaGreaterThanAndFechaCierreIsNotNull(Usuario usuario, Date fecha);

	public void addItem(Long idCarrito, CarritoItem item) throws Exception;

	public void delete(Long idCarrito) throws Exception;

	public void deleteItem(Long idCarrito, Long idItem) throws Exception;
	
	
}
