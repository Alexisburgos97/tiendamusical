package com.aburgos.tiendamusicalweb.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

@ManagedBean
@SessionScoped
public class SessionBean {
	
	private Persona persona;
	
	private ArtistaAlbumDTO artistaAlbumDTO;
	
	private float totalCompra;
	
	//Orden generada por PayPal
	private HttpResponse<Order> order;
	
	private int paso;

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
	 * @return the artistaAlbumDTO
	 */
	public ArtistaAlbumDTO getArtistaAlbumDTO() {
		return artistaAlbumDTO;
	}

	/**
	 * @param artistaAlbumDTO the artistaAlbumDTO to set
	 */
	public void setArtistaAlbumDTO(ArtistaAlbumDTO artistaAlbumDTO) {
		this.artistaAlbumDTO = artistaAlbumDTO;
	}

	/**
	 * @return the totalCompra
	 */
	public float getTotalCompra() {
		return totalCompra;
	}

	/**
	 * @param totalCompra the totalCompra to set
	 */
	public void setTotalCompra(float totalCompra) {
		this.totalCompra = totalCompra;
	}

	public void setOrder(HttpResponse<Order> response) {
		this.order = response;
	}

	/**
	 * @return the order
	 */
	public HttpResponse<Order> getOrder() {
		return order;
	}

	/**
	 * @return the paso
	 */
	public int getPaso() {
		return paso;
	}

	/**
	 * @param paso the paso to set
	 */
	public void setPaso(int paso) {
		this.paso = paso;
	}
	
	

}
