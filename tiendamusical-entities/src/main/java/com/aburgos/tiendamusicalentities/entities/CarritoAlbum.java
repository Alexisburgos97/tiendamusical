/**
 * 
 */
package com.aburgos.tiendamusicalentities.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author DevPredator
 * Clase que representa la cantida de entidades de albums en el carrito de la persona.
 */
@Entity
@Table(name = "carrito_album")
public class CarritoAlbum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCarritoAlbum;
		
	@ManyToOne
	@JoinColumn(name = "idCarrito")
	private Carrito carrito;
	
	@ManyToOne
	@JoinColumn(name = "idAlbum")
	private Album album;
	
	@ManyToOne
	@JoinColumn(name = "idFactura")
	private Factura factura;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "estatus")
	private String estatus;
	
	@Column(name = "fechaCompra")
	private LocalDateTime fechaCompra;

	/**
	 * @return the idCarritoAlbum
	 */
	public Long getIdCarritoAlbum() {
		return idCarritoAlbum;
	}

	/**
	 * @param idCarritoAlbum the idCarritoAlbum to set
	 */
	public void setIdCarritoAlbum(Long idCarritoAlbum) {
		this.idCarritoAlbum = idCarritoAlbum;
	}

	/**
	 * @return the carrito
	 */
	public Carrito getCarrito() {
		return carrito;
	}

	/**
	 * @param carrito the carrito to set
	 */
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the fechaCompra
	 */
	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * @param fechaCompra the fechaCompra to set
	 */
	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
}
