package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.dao.UsuarioDAO;
import net.patrolas.model.Perfil;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = 2555206043530357044L;

	public UsuarioController() {
		super(new UsuarioDAO());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuarioFlash");
		setEntity((Usuario) flash.get("usuarioFlash"));
	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

}
