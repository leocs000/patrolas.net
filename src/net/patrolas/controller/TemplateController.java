package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -2343574977842930993L;
	
//	private Usuario usuarioLogado = null;
	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}

	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Usuario) obj;
	}
	

}