package net.patrolas.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.patrolas.application.Session;
import net.patrolas.application.Util;
import net.patrolas.dao.UsuarioDAO;
import net.patrolas.model.Usuario;
@Named
@ViewScoped
public class EditarPerfilController implements Serializable{
	
	
	private static final long serialVersionUID = 2173254053522356043L;
	
	private Usuario usuario = null;
	
	public EditarPerfilController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuarioFlash");
		setUsuario((Usuario) flash.get("usuarioFlash"));
	}
	
	public void alterar() {
		UsuarioDAO dao = new UsuarioDAO(); 
		
		try {
			dao.alterar(getUsuario());
			Util.addInfoMessage("Alteração realizada com sucesso.");
			
			Session.getInstance().setAttribute("usuarioLogado", getUsuario());
			Util.redirect("/patrolas.net/faces/usuario/perfilUsuario.xhtml");
			
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma alteração.");
			e.printStackTrace();
		}
		
		
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
