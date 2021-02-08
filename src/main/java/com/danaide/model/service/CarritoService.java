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
import com.danaide.model.CarritoItem;
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

	@Override
	public void save(Carrito carrito) {
		cerrarCarritosIncompletos(carrito.getUsuario(), carrito.getFecha());
		carritoDao.save(carrito);
	}

	private void cerrarCarritosIncompletos(Usuario usuario, Date fecha) {
		List<Carrito> lsCarritos = carritoDao.findByUsuarioAndFechaLessThanAndFechaCierreIsNull(usuario, fecha);
		for (Iterator<Carrito> iterator = lsCarritos.iterator(); iterator.hasNext();) {
			Carrito carrito = iterator.next();
			carritoDao.delete(carrito);
		}
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
			for (Iterator<CarritoItem> iteratorItemCarrito = xCarrito.getItems().iterator(); iteratorItemCarrito.hasNext();) {
				CarritoItem itemCarrito = iteratorItemCarrito.next();
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
	public void addItem(Long idCarrito, CarritoItem itemCarrito) throws Exception {
		Carrito carrito = findById(idCarrito);
		if(carrito != null) {
			if(carrito.getFechaCierre() == null) {
				itemCarritoService.save(itemCarrito);
				carrito.getItems().add(itemCarrito);
				save(carrito);		
			} else {
				throw new Exception("No se pueden agregar items a un carrito finalizado");
			}
		} else {
			throw new Exception("El carrito con id " + idCarrito + " no existe");
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

	@Override
	public void delete(Long idCarrito) throws Exception {
		Carrito carrito = findById(idCarrito);
		if(carrito != null) {
			carritoDao.delete(carrito);
		} else {
			throw new Exception("El carrito con id " + idCarrito + " no existe");
		}
	}

	@Override
	public void deleteItem(Long idCarrito, Long idItem) throws Exception {
		itemCarritoService.deleteItem(idItem);
	}
}
