package com.danaide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany (mappedBy = "carrito", fetch = FetchType.EAGER)
    private List<CarritoItem> items;
	
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

	public List<CarritoItem> getItems() {
		if(items == null) {
			items = new ArrayList<CarritoItem>();
		}
		return items;
	}

	public void setItems(List<CarritoItem> items) {
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
		for (Iterator<CarritoItem> iterator = items.iterator(); iterator.hasNext();) {
			CarritoItem itemCarrito = iterator.next();
			precioTotal += itemCarrito.getPrecioTotal();
		}
		if(fechaCierre != null) {
			precioTotal = (precioTotal - getDescuento());
		}
		return precioTotal;
	}

	public Double getDescuento() {
		if(getItems().size()<5) {
			return new Double(0);
		} else {
			try {
				switch (getTipoCarrito()) {
				case "VIP":
					return new Double(700 - bonificacionProductoMasBarato());
				case "PROMOCIONABLE":
					return new Double(500);
				case "COMUN":
					return new Double(200);
				default:
					return new Double(0);
				}
			}catch (NullPointerException e) {
				return new Double(0);	
			}
		}
	}

	private Double bonificacionProductoMasBarato() {
		Double bonificacion = new Double(Double.MAX_VALUE);
		for (Iterator<CarritoItem> iterator = items.iterator(); iterator.hasNext();) {
			CarritoItem itemCarrito = iterator.next();
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
