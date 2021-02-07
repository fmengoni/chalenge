package com.danaide.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaide.model.Usuario;
import com.danaide.model.dao.IUsuarioDao;

@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario findById(Long idUsuario) {
		Optional<Usuario> oUsuario = usuarioDao.findById(idUsuario);
		return oUsuario.isPresent() ? oUsuario.get() : null;
	}

}
