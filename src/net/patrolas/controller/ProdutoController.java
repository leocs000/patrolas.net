package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.dao.ProdutoDAO;
import net.patrolas.model.Produto;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable{
	
	private static final long serialVersionUID = -2345407772675191845L;
	
//	priOate List<Categoria> listaCategoria;

	public ProdutoController() {
		super(new ProdutoDAO());
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("produtoFlash");
		setEntity((Produto)flash.get("produtoFlash"));
	}

	@Override
	public Produto getEntity() {
		if (entity == null)
			entity = new Produto();
		return entity;
	}
	
//	public void obterListaCategoria() {
//		CategoriaDAO dao = new CategoriaDAO();
//		try {
//			setListaCategoria(dao.obterTodos());
//			for (Categoria categoria : listaCategoria) {
//				System.out.println(categoria);
//			}
//		} catch (Exception e) {
//			System.out.println("Erro ao pesquisar as categorias");
//			e.printStackTrace();
//			setListaCategoria(null);
//		}
//	}
//
//	public List<Categoria> getListaCategoria() {
//		if(listaCategoria == null)
//			listaCategoria = new ArrayList<Categoria>();
//		obterListaCategoria();
//		return listaCategoria;
//	}
//
//	public void setListaCategoria(List<Categoria> listaCategoria) {
//		this.listaCategoria = listaCategoria;
//	}
	
	
	
}
