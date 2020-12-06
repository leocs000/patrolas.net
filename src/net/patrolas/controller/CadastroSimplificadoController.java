package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Util;
import net.patrolas.dao.UsuarioDAO;
import net.patrolas.model.Usuario;

@Named
@ViewScoped
public class CadastroSimplificadoController implements Serializable{

	private static final long serialVersionUID = 1914691576896062179L;

	
	private Usuario usuario;
	
	public void incluir() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			
			dao.inserir(getUsuario());
			Util.addInfoMessage("Cadastro realizado com sucesso.");
			Util.redirect("login.xhtml");
		} catch (Exception e) {
			
			Util.addErrorMessage("Erro ao realizar o cadastro");
			e.printStackTrace();
		}
		
	}
	
	public Usuario getUsuario() {
		if(usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
