package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalweb.paypal.PayPalCreateOrder;
import com.aburgos.tiendamusicalweb.session.SessionBean;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class PasosController {
	
	private static final Logger LOGGER = LogManager.getLogger(PasosController.class);
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@PostConstruct
	public void init() {
		LOGGER.info("Ingresando a PasosController..");
		
	}
	
	public void cambiarPaso(String url, int paso) {
		try {
			this.sessionBean.setPaso(paso);
			
			CommonUtils.redireccionar(url);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡UPS!", "Hubo un problema al tratar de entrar al siguiente paso de la compra");
			e.printStackTrace();
		}
	}

	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	

}
