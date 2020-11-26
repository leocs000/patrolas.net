package net.patrolas.model;

import java.time.LocalDate;

public class Produto {
	private Integer id;
	private Integer codigo;
	private String categoria;
	private String fabricante;
	private String modelo;
	private LocalDate anoFabricacao;
	private Boolean estoque;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public LocalDate getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(LocalDate anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public Boolean getEstoque() {
		return estoque;
	}
	public void setEstoque(Boolean estoque) {
		this.estoque = estoque;
	}

}
