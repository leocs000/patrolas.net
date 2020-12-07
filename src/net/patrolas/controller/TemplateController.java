package net.patrolas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.dao.CategoriaDAO;
import net.patrolas.model.Categoria;
import net.patrolas.model.Perfil;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = 5714624053897073405L;

	private boolean adm = false;
	private boolean func = false;
	private boolean usu = false;
	
	private List<Categoria> listaCategoria;

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("/patrolas.net/faces/login.xhtml");
	}

	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Usuario) obj;
	}

	public List<Categoria> getListaCategoria() {
		if(listaCategoria == null)
			listaCategoria = new ArrayList<Categoria>();
		
		CategoriaDAO dao = new CategoriaDAO();
		
		try {
			
			setListaCategoria(dao.obterTodos());
		} catch (Exception e) {
			System.out.println("Não foi possivel realizar a consulta. Tente novamente mais tarde");
			e.printStackTrace();
			setListaCategoria(null);
		}
		
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
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