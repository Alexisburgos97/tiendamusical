package com.aburgos.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalservices.service.CarritoService;
import com.aburgos.tiendamusicalweb.session.SessionBean;

@ManagedBean
@ViewScoped
public class DetalleController {
	
	private static final Logger LOGGER = LogManager.getLogger(DetalleController.class);

	private Integer cantidadSeleccionada;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@PostConstruct
	public void init() {
		this.cantidadSeleccionada = 1;
	}
	
	public void agregarAlbumCarrito(ArtistaAlbumDTO artistaAlbumDTO) {
		LOGGER.info("ingresando al detalle, cantidad seleccionada: " + this.cantidadSeleccionada);
		
		CarritoAlbum carritoAlbumAgregado = this.carritoServiceImpl.guardarAlbumsCarrito(artistaAlbumDTO, sessionBean.getPersona().getCarrito(), cantidadSeleccionada);
		
		this.sessionBean.getPersona().getCarrito().getCarritoAlbum().add(carritoAlbumAgregado);
	}

	/**
	 * @return the cantidadSeleccionada
	 */
	public Integer getCantidadSeleccionada() {
		return cantidadSeleccionada;
	}

	/**
	 * @param cantidadSeleccionada the cantidadSeleccionada to set
	 */
	public void setCantidadSeleccionada(Integer cantidadSeleccionada) {
		this.cantidadSeleccionada = cantidadSeleccionada;
	}

	/**
	 * @return the carritoServiceImpl
	 */
	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	/**
	 * @param carritoServiceImpl the carritoServiceImpl to set
	 */
	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
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
