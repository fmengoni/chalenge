package com.danaide.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaide.model.Usuario;
import com.danaide.model.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> getUsuarios(){
		return usuarioService.getUsuarios();
	}
}
