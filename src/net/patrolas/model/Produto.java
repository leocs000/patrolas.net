package net.patrolas.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Produto {
	private Integer id;
	
	@NotBlank(message = "O código não pode ser nulo")
	private Integer codigo;
	
	@NotNull(message = "Escolha uma categoria")
	private Categoria categoria;
	
	@NotBlank(message = "O Titulo não pode estar em branco")
	@Size(min = 1, max = 100, message = "O titulo deve ter entre 1 e 100 caracteres")
	private String titulo;
	private String descriçao;
	private String fabricante;
	private String modelo;
	private Integer anoFabricacao;	
	
	@NotNull(message = "Informe o preço")
	private Double preco;
	private Integer estoque = 0;
	
	public Produto() {
		
	}
	
	public Produto(Integer id) {
		super();
		this.id = id;
	}
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
	public Categoria getCategoria() {
		if(categoria == null)
			categoria = new Categoria();
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescriçao() {
		return descriçao;
	}

	public void setDescriçao(String descriçao) {
		this.descriçao = descriçao;
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
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
