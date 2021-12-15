package com.aburgos.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.aburgos.tiendamusicalentities.entities.Carrito;
import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalservices.service.CarritoService;
import com.aburgos.tiendamusicalweb.session.SessionBean;

@ManagedBean
@ViewScoped
public class CarritoController {
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	private float total;
	
	@PostConstruct
	public void init() {
		this.sessionBean.setPaso(0);
		this.calcularTotal();
	}
	
	public void calcularTotal() {
		
		total = this.carritoServiceImpl.calcularTotal(sessionBean.getPersona().getCarrito());
		
		sessionBean.setTotalCompra(total);	
	}
	
	public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
		
		this.carritoServiceImpl.eliminarAlbumCarrito(carritoAlbum);
		this.sessionBean.getPersona().getCarrito().getCarritoAlbum().remove(carritoAlbum);
		this.calcularTotal();
		
	}
	
	public void actualizarCantidadAlbum(CarritoAlbum carritoAlbum ) {
		
		float totalCompra = this.carritoServiceImpl.actualizarCantidadAlbum(carritoAlbum, this.sessionBean.getPersona().getCarrito());
		
		this.sessionBean.setTotalCompra(totalCompra);
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
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
	

}
