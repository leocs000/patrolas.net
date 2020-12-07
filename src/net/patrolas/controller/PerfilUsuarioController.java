package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.dao.UsuarioDAO;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class PerfilUsuarioController implements Serializable{

	private static final long serialVersionUID = 2918609772176594882L;

	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Usuario) obj;
	}
	
	public void alterarUsuario() {
		Util.redirect("/patrolas.net/faces/usuario/editarPerfil.xhtml");
	}

	public void editar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario editarUsuario = null;
		try {
			editarUsuario = dao.obterUm(getUsuarioLogado());
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar o usuario no banco de dados.");
			return;
		}

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", editarUsuario);
		alterarUsuario();
	}


}
