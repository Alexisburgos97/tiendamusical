package com.aburgos.tiendamusicalentities.dto;

import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;

public class AlbumTopTenDTO {
	
	private CarritoAlbum carritoAlbum;
	
	private long cantidadSuma;

	public AlbumTopTenDTO() {
		super();
	}

	public AlbumTopTenDTO(CarritoAlbum carritoAlbum, long cantidadSuma) {
		this.carritoAlbum = carritoAlbum;
		this.cantidadSuma = cantidadSuma;
	}

	/**
	 * @return the carritoAlbum
	 */
	public CarritoAlbum getCarritoAlbum() {
		return carritoAlbum;
	}

	/**
	 * @param carritoAlbum the carritoAlbum to set
	 */
	public void setCarritoAlbum(CarritoAlbum carritoAlbum) {
		this.carritoAlbum = carritoAlbum;
	}

	/**
	 * @return the cantidadSuma
	 */
	public long getCantidadSuma() {
		return cantidadSuma;
	}

	/**
	 * @param cantidadSuma the cantidadSuma to set
	 */
	public void setCantidadSuma(long cantidadSuma) {
		this.cantidadSuma = cantidadSuma;
	}
	
	

}
