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
import net.patrolas.dao.ProdutoDAO;
import net.patrolas.model.ItemVenda;
import net.patrolas.model.Produto;

@Named
@ViewScoped
public class VendaController implements Serializable {

	private static final long serialVersionUID = 5874765457553829169L;

	
	private Integer tipoFiltro;
	private String filtro;
	private List<Produto> listaProduto;
	
	
	
	public VendaController() {
		super();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("pesquisaProdutoFlash");
		setListaProduto((List<Produto>) flash.get("pesquisaProdutoFlash"));

	}

	public void pesquisar() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			setListaProduto(dao.obterListaProdutoComEstoque(tipoFiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaProduto(null);
		}
	}
	
	public void addCarrinho(Produto produto) {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			// obtendo os dados atuais da produto
			produto = dao.obterUm(produto);
			
			List<ItemVenda> listaItemVenda = null;
			Object obj = Session.getInstance().getAttribute("carrinho");
			
			if (obj == null) 
				listaItemVenda = new ArrayList<ItemVenda>();
			else 
				listaItemVenda = (List<ItemVenda>) obj;
			
			// montando o item de venda
			ItemVenda item = new ItemVenda();
			item.setProduto(produto);
			item.setPreco(produto.getPreco());
			listaItemVenda.add(item);
			
			// atualizando a sessao do carrinho de compras
			Session.getInstance().setAttribute("carrinho", listaItemVenda);
			
			Util.addInfoMessage("O produto: " + produto.getTitulo() + " foi adicionado ao carrinho.");
			
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao adicionar o produto ao carrinho. Tente novamente.");
		}
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

	public List<Produto> getListaProduto() {
		if (listaProduto == null)
			listaProduto = new ArrayList<Produto>();
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

}
