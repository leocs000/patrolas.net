package net.patrolas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.dao.CategoriaDAO;
import net.patrolas.dao.ProdutoDAO;
import net.patrolas.model.Categoria;
import net.patrolas.model.ItemVenda;
import net.patrolas.model.Produto;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = 5714624053897073405L;
	
	private String filtro;
	private AcessoController acesso;

	private List<Categoria> listaCategoria;
	private List<Produto> listaProduto;
	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("/patrolas.net/faces/login.xhtml");
	}
	
	public Integer getQtdCarrinho(){
		List<ItemVenda> listaItemVenda = null;
		Object obj = Session.getInstance().getAttribute("carrinho");
		
		if (obj == null) 
			return 0;
		else 
			listaItemVenda = (List<ItemVenda>) obj;
		
		return listaItemVenda.size();
	}
	
	public void pesquisar() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			setListaProduto(dao.obterListaProdutoComEstoque(filtro));
		} catch (Exception e) {
			System.out.println("Não foi possivel realizar a busca feita no input do menu");
			e.printStackTrace();
		}
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pesquisaProdutoFlash", listaProduto);
		Util.redirect("/patrolas.net/faces/venda.xhtml");
	}
	
	public AcessoController getAcesso() {
		if(acesso == null)
			acesso = new AcessoController();
		return acesso;
	}

	public void setAcesso(AcessoController acesso) {
		this.acesso = acesso;
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

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Produto> getListaProduto() {
		if(listaProduto == null)
			listaProduto = new ArrayList<Produto>();
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	

}