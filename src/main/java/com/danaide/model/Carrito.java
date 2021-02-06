package com.danaide.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

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
	
	@OneToMany(mappedBy = "carrito")
    Set<ItemCarrito> items;
	
	@Column
	private String tipoCarrito;
	
	@Column
	private String fecha;
	
	@ManyToOne
	@JoinColumn(name = "idCarrito", insertable = false, updatable = false)
	private Usuario usuario;

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

	public String getTipoCarrito() {
		return tipoCarrito;
	}

	public void setTipoCarrito(String tipoCarrito) {
		this.tipoCarrito = tipoCarrito;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
