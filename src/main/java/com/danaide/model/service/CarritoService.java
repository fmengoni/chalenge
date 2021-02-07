package com.danaide.model.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaide.model.Carrito;
import com.danaide.model.FechaPromocionable;
import com.danaide.model.ItemCarrito;
import com.danaide.model.ItemCarritoKey;
import com.danaide.model.Producto;
import com.danaide.model.Usuario;
import com.danaide.model.dao.ICarritoDao;

@Service
public class CarritoService implements ICarritoService {
	@Autowired
	private ICarritoDao carritoDao;
	@Autowired
	private IFechaPromocionableService fechaPromocionableService;
	@Autowired
	private IItemCarritoService itemCarritoService;
	@Autowired
	private IProductoService productoService;

	@Override
	public void save(Carrito carrito) {
		//Previo a insertar el carrito determinamos el tipo (VIP, PROMOCIONABLE, COMUN)
//		carrito.setTipoCarrito(determinarTipoDeCarrito(carrito));
		
		carritoDao.save(carrito);
	}

	private String determinarTipoDeCarrito(Carrito carrito) {
		if(!esVip(carrito)) {
			if(!esPromocionable(carrito)) {
				return "COMUN";
			} else {
				return "PROMOCIONABLE";
			}
		} else {
			return "VIP";
		}
	}

	private Boolean esPromocionable(Carrito carrito) {
		FechaPromocionable fechaPromocionable = fechaPromocionableService.findById(carrito.getFecha());
		return fechaPromocionable != null;
	}

	private Boolean esVip(Carrito carrito) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(carrito.getFecha());
		cal.add(Calendar.DATE, -30);
		
		List<Carrito> lsCarritos = findByUsuarioAndFechaGreaterThanAndFechaCierreIsNotNull(carrito.getUsuario(), cal.getTime());
		
		Double montoTotal = new Double(0);
		for (Iterator<Carrito> iterator = lsCarritos.iterator(); iterator.hasNext();) {
			Carrito xCarrito = iterator.next();
			for (Iterator<ItemCarrito> iteratorItemCarrito = xCarrito.getItems().iterator(); iteratorItemCarrito.hasNext();) {
				ItemCarrito itemCarrito = iteratorItemCarrito.next();
				montoTotal += itemCarrito.getPrecioTotal();
			}
		}
		
		return montoTotal.compareTo(new Double(10000)) >= 0;
	}

	@Override
	public List<Carrito> getCarritos() {
		return (List<Carrito>) carritoDao.findAll();
	}

	@Override
	public List<Carrito> findByUsuario(Usuario usuario) {
		List<Carrito> lsCarritos = carritoDao.findByUsuario(usuario);
		for (Iterator<Carrito> iterator = lsCarritos.iterator(); iterator.hasNext();) {
			Carrito carrito = iterator.next();
			carrito.setItems(itemCarritoService.findByCarritoIdCarrito(carrito.getIdCarrito()));
		}
		return lsCarritos;
	}

	@Override
	public List<Carrito> findByUsuarioAndFechaGreaterThanAndFechaCierreIsNotNull(Usuario usuario, Date fecha) {
		return carritoDao.findByUsuarioAndFechaGreaterThanAndFechaCierreIsNotNull(usuario, fecha);
	}

	@Override
	public void addItem(Long idCarrito, ItemCarrito itemCarrito) {
		Carrito carrito = findById(idCarrito);
		if(carrito != null) {
			itemCarrito.setCarrito(carrito);
			itemCarrito.setProducto(productoService.findById(itemCarrito.getId().getIdProducto()));
			itemCarritoService.save(itemCarrito);
			carrito.getItems().add(itemCarrito);
			save(carrito);	
		}
	}

	@Override
	public Carrito findById(Long idCarrito) {
		Optional<Carrito> oCarrito = carritoDao.findById(idCarrito);
		return oCarrito.isPresent() ? oCarrito.get() : null;
	}

	@Override
	public void cerrarCarrito(Carrito carrito) {
		carrito.setTipoCarrito(determinarTipoDeCarrito(carrito));
		carrito.setFechaCierre(new Date());
		
		save(carrito);
	}
}
