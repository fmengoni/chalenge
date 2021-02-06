package com.danaide.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ItemCarrito {
	@EmbeddedId
    ItemCarritoKey id;
	
	@ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "idProducto")
    Producto producto;
	
	@ManyToOne
	@MapsId("idCarrito")
	@JoinColumn(name="idCarrito")
	Carrito carrito;
	
	@Column(name = "cantidad")
	private Integer cantidad;
}
