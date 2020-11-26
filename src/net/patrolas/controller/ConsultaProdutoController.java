package net.patrolas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Util;
import net.patrolas.dao.ProdutoDAO;
import net.patrolas.model.Produto;
@Named
@ViewScoped
public class ConsultaProdutoController {
	private Integer tipoFiltro;
	private String filtro;
	private List<Produto> listaProduto;

	public void novoProduto() {
		Util.redirect("Produto.xhtml");
	}

	public void pesquisar() {
		ProdutoDAO dao = new ProdutoDAO();
		System.out.println(tipoFiltro + " " + filtro);
		try {
			
			setListaProduto(dao.obterListaProduto(tipoFiltro, filtro));
		} catch (Exception e) {
			System.out.println("Não foi possivel realizar a consulta. Tente novamente mais tarde");
			e.printStackTrace();
			setListaProduto(null);
		}
	}

	public void editar(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		Produto editarProduto = null;
		try {
			editarProduto = dao.obterUm(produto);
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar o produto no banco de dados.");
			return;
		}

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("produtoFlash", editarProduto);
		novoProduto();
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
