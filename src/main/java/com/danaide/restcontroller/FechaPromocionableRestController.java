package com.danaide.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaide.model.FechaPromocionable;
import com.danaide.model.service.IFechaPromocionableService;

@RestController
@RequestMapping("/fechasPromocionables")
public class FechaPromocionableRestController {
	@Autowired
	private IFechaPromocionableService fechaPromocionableService;
	
	@GetMapping
	public List<FechaPromocionable> getFechasPromocionables(){
		return fechaPromocionableService.getFechasPromocionables();
	}
}
