package net.patrolas.controller;

import net.patrolas.application.Session;
import net.patrolas.model.Perfil;
import net.patrolas.model.Usuario;

public class AcessoController {
	private boolean adm = false;
	private boolean func = false;
	private boolean usu = false;
	
	
	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Usuario) obj;
	}
	
	public boolean isAdm() {
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
