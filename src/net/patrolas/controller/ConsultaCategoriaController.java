package net.patrolas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Util;
import net.patrolas.dao.CategoriaDAO;
import net.patrolas.model.Categoria;

@Named
@ViewScoped
public class ConsultaCategoriaController implements Serializable{


	private static final long serialVersionUID = 5138241145540530394L;

	private String filtro;
	private List<Categoria> listaCategoria;

	public void novaCategoria() {
		Util.redirect("/patrolas.net/faces/funcionario/categoria.xhtml");
	}

	public void pesquisar() {
		CategoriaDAO dao = new CategoriaDAO();
		try {
			
			setListaCategoria(dao.obterTodos());
		} catch (Exception e) {
			System.out.println("Não foi possivel realizar a consulta. Tente novamente mais tarde");
			e.printStackTrace();
			setListaCategoria(null);
		}
	}

	public void editar(Categoria categoria) {
		CategoriaDAO dao = new CategoriaDAO();
		Categoria editarCategoria = null;
		try {
			editarCategoria = dao.obterUm(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar o categoria no banco de dados.");
			return;
		}

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("categoriaFlash", editarCategoria);
		novaCategoria();
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Categoria> getListaCategoria() {
		if(listaCategoria == null)
			listaCategoria = new ArrayList<Categoria>();
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}
}
