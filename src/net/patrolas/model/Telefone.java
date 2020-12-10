package net.patrolas.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Telefone {
	private Integer id;
	
	@Size(min = 2, max = 3, message = "DDD inv�lido")
	@NotBlank(message = "Informe o DDD")
	private String codigoArea;
	
	@Size(min = 9, max = 10, message = "numero inv�lido")
	@NotBlank(message = "Informe o n�mero")
	private String numero;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
