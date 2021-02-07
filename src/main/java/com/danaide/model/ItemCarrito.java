package com.danaide.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class ItemCarrito {
	@EmbeddedId
    ItemCarritoKey id;
	
	@OneToOne
    @MapsId("idProducto")
    @JoinColumn(name = "idProducto")
    Producto producto;
	
	@OneToOne
	@MapsId("idCarrito")
	@JoinColumn(name="idCarrito")
	Carrito carrito;
	
	@Column(name = "cantidad")
	private Integer cantidad;

	public ItemCarritoKey getId() {
		return id;
	}

	public void setId(ItemCarritoKey id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getPrecioTotal() {
		return producto.getPrecio() * cantidad;
	}
}
