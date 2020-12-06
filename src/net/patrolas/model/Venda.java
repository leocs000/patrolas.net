package net.patrolas.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Venda {
	private Integer id;
	private LocalDateTime data;
	
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private List<ItemVenda> listaItemVenda;
	
	public Double getTotalVenda() {
		Double total = 0.0;
		for (ItemVenda itemVenda : listaItemVenda) {
			total += itemVenda.getPreco();
		}
		return total;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}
	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	
}
