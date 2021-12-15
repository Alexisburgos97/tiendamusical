package com.aburgos.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalentities.entities.Persona;
import com.aburgos.tiendamusicalservices.service.InfoPersonalService;
import com.aburgos.tiendamusicalweb.session.SessionBean;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class InfoPersonalController {
	
	private static final Logger LOGGER = LogManager.getLogger(InfoPersonalController.class);
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{infoPersonalServiceImpl}")
	private InfoPersonalService infoPersonalServiceImpl;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void actualizarPersona() {
		Persona personaActualizada = this.infoPersonalServiceImpl.actualizarPersona(sessionBean.getPersona());
		
		if(personaActualizada != null) {
			this.sessionBean.setPersona(personaActualizada);
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK" , "Tu información ha sido actualizada, vuelve a iniciar sesión para aplicar los cambios" );
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

	/**
	 * @return the infoPersonalServiceImpl
	 */
	public InfoPersonalService getInfoPersonalServiceImpl() {
		return infoPersonalServiceImpl;
	}

	/**
	 * @param infoPersonalServiceImpl the infoPersonalServiceImpl to set
	 */
	public void setInfoPersonalServiceImpl(InfoPersonalService infoPersonalServiceImpl) {
		this.infoPersonalServiceImpl = infoPersonalServiceImpl;
	}
	
	

}
