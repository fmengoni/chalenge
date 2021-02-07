package com.danaide.model.service;

import com.danaide.model.FechaPromocionable;

import java.util.Date;
import java.util.List;

public interface IFechaPromocionableService {
	public List<FechaPromocionable> getFechasPromocionables(); 
	
	public FechaPromocionable findById(Date id);
}
