package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.dao.UsuarioDAO;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	
	
	private static final long serialVersionUID = 2555206043530357044L;

	public UsuarioController() {
		super(new UsuarioDAO());
	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}


	}

