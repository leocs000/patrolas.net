package net.patrolas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.dao.CategoriaDAO;
import net.patrolas.model.Categoria;

@Named
@ViewScoped
public class CategoriaController extends Controller<Categoria> implements Serializable{

	private List<Categoria> listaCategoria;
	
	private static final long serialVersionUID = 5875132057190864222L;

	public CategoriaController() {
		super(new CategoriaDAO());
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("categoriaFlash");
		setEntity((Categoria)flash.get("categoriaFlash"));
	}
	

	@Override
	public Categoria getEntity() {
		if(entity == null)
			entity = new Categoria();
		return entity;
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
