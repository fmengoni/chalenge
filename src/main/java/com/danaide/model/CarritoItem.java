package com.danaide.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrito_items")
public class CarritoItem {
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@ManyToOne (fetch = FetchType.LAZY) 
    @JoinColumn(name = "fk_Carrito", nullable = false, updatable = false)
    private Carrito carrito;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Carrito getCarrito() {
//		return carrito;
//	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
}
