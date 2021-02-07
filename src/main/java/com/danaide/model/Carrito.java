package com.danaide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="carritos")
public class Carrito implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCarrito;
	
	@OneToMany (mappedBy = "carrito")
    private List<ItemCarrito> items;
	
	@Column
	private String tipoCarrito;
	
	
	@Column
	private Date fecha;
	
	@Column
	private Date fechaCierre;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public List<ItemCarrito> getItems() {
		if(items == null) {
			items = new ArrayList<ItemCarrito>();
		}
		return items;
	}

	public void setItems(List<ItemCarrito> items) {
		this.items = items;
	}

	public String getTipoCarrito() {
		return tipoCarrito;
	}

	public void setTipoCarrito(String tipoCarrito) {
		this.tipoCarrito = tipoCarrito;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Double getPrecioTotal() {
		Double precioTotal = new Double(0);
		for (Iterator<ItemCarrito> iterator = items.iterator(); iterator.hasNext();) {
			ItemCarrito itemCarrito = iterator.next();
			precioTotal += itemCarrito.getPrecioTotal();
		}
		return precioTotal;
	}

	public Double getDescuento(Double precioTotal) {
		if(getItems().size()<5) {
			return precioTotal;
		} else {
			switch (getTipoCarrito()) {
			case "VIP":
				return precioTotal - 700 - bonificacionProductoMasBarato();
			case "PROMOCIONABLE":
				return precioTotal - 500;
			case "COMUN":
				return precioTotal - 200;
			}
		}
		return null;
	}

	private Double bonificacionProductoMasBarato() {
		Double bonificacion = new Double(Double.MAX_VALUE);
		for (Iterator<ItemCarrito> iterator = items.iterator(); iterator.hasNext();) {
			ItemCarrito itemCarrito = iterator.next();
			if(itemCarrito.getPrecioTotal().compareTo(bonificacion) < 0) {
				bonificacion = itemCarrito.getPrecioTotal();
			}
		}
		return bonificacion;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
}
