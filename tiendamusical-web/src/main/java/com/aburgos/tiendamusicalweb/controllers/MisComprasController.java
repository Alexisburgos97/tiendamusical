package com.aburgos.tiendamusicalweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalentities.entities.Factura;
import com.aburgos.tiendamusicalservices.service.FacturaService;
import com.aburgos.tiendamusicalweb.session.SessionBean;

@ManagedBean
@ViewScoped
public class MisComprasController {
	
	private static final Logger LOGGER = LogManager.getLogger(MisComprasController.class);
	
	private List<Factura> facturas;
	
	/***
	 * Lista de albums en el carrito comprado en la factura
	 */
	private List<CarritoAlbum> carritosAlbum;
	
	@ManagedProperty("#{facturaServiceImpl}")
	private FacturaService facturaServiceImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@PostConstruct
	public void init() {
		this.consultarFacturasPorPersona();
	}
	
	public void consultarFacturasPorPersona() {
		this.facturas = this.facturaServiceImpl.consultarFacturaPersona(sessionBean.getPersona());
	}
	
	/***
	 * Metodo que permite mostrar la información del detalle de la compra.
	 * @param carritoAlbum
	 */
	public void mostrarDetalle(List<CarritoAlbum> carritoAlbum) {
		
		this.carritosAlbum = carritoAlbum;
	}

	/**
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @param facturas the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * @return the facturaServiceImpl
	 */
	public FacturaService getFacturaServiceImpl() {
		return facturaServiceImpl;
	}

	/**
	 * @param facturaServiceImpl the facturaServiceImpl to set
	 */
	public void setFacturaServiceImpl(FacturaService facturaServiceImpl) {
		this.facturaServiceImpl = facturaServiceImpl;
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
	 * @return the carritosAlbum
	 */
	public List<CarritoAlbum> getCarritosAlbum() {
		return carritosAlbum;
	}

	/**
	 * @param carritosAlbum the carritosAlbum to set
	 */
	public void setCarritosAlbum(List<CarritoAlbum> carritosAlbum) {
		this.carritosAlbum = carritosAlbum;
	}
	
	

}
