package net.patrolas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.dao.VendaDAO;
import net.patrolas.model.ItemVenda;
import net.patrolas.model.Usuario;
import net.patrolas.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -4322494578346655162L;

	private Venda venda;

	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		// obtendo o carrinho da sessao
		Object obj = Session.getInstance().getAttribute("carrinho");
		if (obj != null)
			venda.setListaItemVenda((List<ItemVenda>) obj);
		
		return venda;
	}
	
	public void remover(ItemVenda itemVenda) {
		List<ItemVenda> listaItemVenda = null;
		Object obj = Session.getInstance().getAttribute("carrinho");
		
		if (obj == null) 
			listaItemVenda = new ArrayList<ItemVenda>();
		else 
			listaItemVenda = (List<ItemVenda>) obj;
		
		listaItemVenda.remove(itemVenda);
		
		// atualizando a sessao do carrinho de compras
		Session.getInstance().setAttribute("carrinho", listaItemVenda);
	}
	
	public void finalizar() {
		// obtendo o usuario da sessao
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null) {
			Util.addErrorMessage("Para finalizar a venda o usuário deve estar logado.");
			return;
		}
		
		// adicionando o usuario logado na venda
		getVenda().setUsuario((Usuario) obj);
		
		VendaDAO dao = new VendaDAO();
		try {
			dao.inserir(getVenda());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			
			// limpando o carrinho
			Session.getInstance().setAttribute("carrinho", null);
			setVenda(null);
			
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
		
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}