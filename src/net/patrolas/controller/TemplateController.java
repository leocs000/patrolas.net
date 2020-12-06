package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.model.Perfil;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = 5714624053897073405L;

	private boolean adm = false;
	private boolean func = false;
	private boolean usu = false;

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

	public boolean getAdm() {
		if (getUsuarioLogado() != null) {
			if (Perfil.ADMINISTRADOR.equals(getUsuarioLogado().getPerfil()))
				setAdm(true);
		}
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public boolean isFunc() {
		if (getUsuarioLogado() != null) {
			if (Perfil.FUNCIONARIO.equals(getUsuarioLogado().getPerfil()) && getUsuarioLogado() != null)
				setFunc(true);
		}
		return func;
	}

	public void setFunc(boolean func) {
		this.func = func;
	}

	public boolean isUsu() {
		if (getUsuarioLogado() != null) {
			if (Perfil.USUARIO.equals(getUsuarioLogado().getPerfil()) && getUsuarioLogado() != null)
				setUsu(true);
		}
		return usu;
	}

	public void setUsu(boolean usu) {
		this.usu = usu;
	}

}