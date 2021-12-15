package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalweb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class PagesController {
	
	private static final Logger LOGGER = LogManager.getLogger(PagesController.class);

	@PostConstruct
	public void init() {
		
	}
	
	/***
	 * Metodo que permite redireccionar entre pantallas de la aplicación
	 * @param pagina
	 */
	public void redireccionarPagina(String pagina) {
		LOGGER.info("Ingresando al metodo de redireccionar pagina...");
		try {
			CommonUtils.redireccionar(pagina);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un error al cambiar a la pantalla de " + pagina);
			e.printStackTrace();
		}
	}
	
	
	
}
