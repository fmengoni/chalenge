package com.danaide.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@OneToMany(mappedBy = "carrito")
    Set<ItemCarrito> items;

	public Long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Set<ItemCarrito> getItems() {
		return items;
	}

	public void setItems(Set<ItemCarrito> items) {
		this.items = items;
	}
}
