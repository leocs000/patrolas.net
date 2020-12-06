package net.patrolas.model;

import javax.validation.constraints.NotBlank;

public class Categoria {
	
	private Integer id;
	@NotBlank(message = "O campo não pode estar em branco")
	private String categoria;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
