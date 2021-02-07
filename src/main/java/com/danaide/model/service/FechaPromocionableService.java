package com.danaide.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaide.model.FechaPromocionable;
import com.danaide.model.dao.IFechaPromocionableDao;

@Service
public class FechaPromocionableService implements IFechaPromocionableService {
	@Autowired
	private IFechaPromocionableDao fechaPromocionableDao;

	@Override
	public List<FechaPromocionable> getFechasPromocionables() {
		return (List<FechaPromocionable>) fechaPromocionableDao.findAll();
	}

	@Override
	public FechaPromocionable findById(Date id) {
		Optional<FechaPromocionable> oFechaPromocionable = fechaPromocionableDao.findById(id);
		return oFechaPromocionable.isPresent() ? oFechaPromocionable.get() : null;
	}

}
