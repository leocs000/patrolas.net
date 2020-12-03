package net.patrolas.model;

public enum Perfil {
	ADMINISTRADOR (1, "Administrador"),
	FUNCIONARIO (2, "Funcionário"),
	USUARIO (3, "Usuario");
	
	private int id;
	private String label;
	
	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static Perfil valueOf(int id) {
		for (Perfil perfil : values()) {
			if (id == perfil.getId()) {
				return perfil;
			}
		}
		return null;
	}
}
