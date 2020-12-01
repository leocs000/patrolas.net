package net.patrolas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Util;
import net.patrolas.dao.UsuarioDAO;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class ConsultaUsuarioController implements Serializable{
	
	private static final long serialVersionUID = 9004352094854931507L;

	
	private Integer tipoFiltro;
	private String filtro;
	private List<Usuario> listaUsuario;

	public void novoUsuario() {
		Util.redirect("index.xhtml");
	}

	public void pesquisar() {
		UsuarioDAO dao = new UsuarioDAO();
		System.out.println(tipoFiltro + " " + filtro);
		try {
			
			setListaUsuario(dao.obterListaUsuario(tipoFiltro, filtro));
		} catch (Exception e) {
			System.out.println("Não foi possivel realizar a consulta. Tente novamente mais tarde");
			e.printStackTrace();
			setListaUsuario(null);
		}
	}

	public void editar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario editarUsuario = null;
		try {
			editarUsuario = dao.obterUm(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar o usuario no banco de dados.");
			return;
		}

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", editarUsuario);
		novoUsuario();
	}

	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null)
			listaUsuario = new ArrayList<Usuario>();
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
}
