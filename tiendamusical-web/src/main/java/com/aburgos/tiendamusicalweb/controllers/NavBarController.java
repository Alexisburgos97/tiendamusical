package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aburgos.tiendamusicalweb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class NavBarController {
	
	
	@PostConstruct
	public void init() {
		
	}
	
	public void redireccionar() {
		try {
			CommonUtils.redireccionar("/pages/cliente/carrito.xhtml");
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "¡UPS!", "Hubo un problema para ingresar al carrito ");
			e.printStackTrace();
		}
	}

}
