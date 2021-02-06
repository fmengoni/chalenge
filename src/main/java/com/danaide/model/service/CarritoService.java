package com.danaide.model.service;

import org.springframework.beans.factory.annotation.Autowired;

public class CarritoService implements ICarritoService {
	@Autowired
	private ICarritoService carritoService;
}
