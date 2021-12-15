package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalentities.entities.Carrito;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.aburgos.tiendamusicalentities.entities.Rol;
import com.aburgos.tiendamusicalservices.service.RegistroService;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class RegistroController {
	
	/**
	 * Objeto que permite mostrar los mensajes de LOG en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(RegistroController.class);
	
	/**
	 * Objeto que guarda una persona
	 */
	private Persona persona;
	
	
	@ManagedProperty("#{registroServiceImpl}")	
	private RegistroService registroServiceImpl;
	
	/**
	 * Inicializando la pantalla de registro.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de registro..");
		this.limpiarComponentes();
	}
	
	
	/**
	 * Metodo que permite inicializar o limpiar los componentes u objetos utilizados en el formulario.
	 */
	public void limpiarComponentes() {
		
		this.persona = new Persona();
		this.persona.setRol(new Rol());
		this.persona.setCarrito(new Carrito());
	}
	
	/**
	 * Metodo que permite guardar el registro de persona.
	 * @throws IOException 
	 */
	public void guardarRegistro() throws IOException {
		
		Persona personaRegistrada = this.registroServiceImpl.guardarPersona(this.persona);
		
		if (personaRegistrada.getIdPersona() != null) {
			
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Registrado en el sistema exitósamente");
			this.limpiarComponentes();
			
			CommonUtils.redireccionar("/login.xhtml");
		}else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un error al registrarse en el sistema, favor de contactar con soporte.");
		}
			
	}


	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}


	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	/**
	 * @return the registroServiceImpl
	 */
	public RegistroService getRegistroServiceImpl() {
		return registroServiceImpl;
	}


	/**
	 * @param registroServiceImpl the registroServiceImpl to set
	 */
	public void setRegistroServiceImpl(RegistroService registroServiceImpl) {
		this.registroServiceImpl = registroServiceImpl;
	}
	
	

}
