package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalentities.entities.Factura;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.aburgos.tiendamusicalservices.service.CarritoService;
import com.aburgos.tiendamusicalservices.service.FacturaService;
import com.aburgos.tiendamusicalweb.session.SessionBean;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

@ManagedBean
@ViewScoped
public class PagoController {
	
	private static final Logger LOGGER = LogManager.getLogger(PagoController.class);
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{facturaServiceImpl}")
	private FacturaService facturaServiceImpl;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;

	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando el proceso de pago..");
	}
	
	public void guardarFactura() {
		LOGGER.info("Guardando factura..");
		
		//Se obtiene la respuesta de la orden de compra generada por PayPal.
		HttpResponse<Order> order = this.sessionBean.getOrder();
		
		Persona persona = this.sessionBean.getPersona();
		
		Factura factura = new Factura();
		
		//Se ejecuta la función que permite guardar la factura y orden del cliente.
		Factura facturaGenerada = this.facturaServiceImpl.guardarFactura(factura, order.result(), persona);
		
		if(facturaGenerada != null) {
			//CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Se ha generado la factura exitosamente!");
			
			//Se actualiza el estatus de los productos del carrito de la persona y se le asigna 
			//la factura generada.
			boolean productosCarritoActualizados = 
					this.carritoServiceImpl.actualizarCarritoAlbum(persona.getCarrito().getCarritoAlbum(), facturaGenerada);
			
			if( productosCarritoActualizados ) {
				this.sessionBean.getPersona().getCarrito().setCarritoAlbum(new ArrayList<CarritoAlbum>());
				this.cambiarPaso("/pages/cliente/confirmacion.xhtml", 2);
			}
			
		}else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "ERROR!", "No se ha generado la factura");
		}
		
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
	
	
}
