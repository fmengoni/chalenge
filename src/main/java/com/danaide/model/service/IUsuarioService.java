package com.danaide.model.service;

import java.util.List;

import com.danaide.model.Usuario;

public interface IUsuarioService {
	public List<Usuario> getUsuarios();
	
	public Usuario findById(Long idUsuario);
	
}
