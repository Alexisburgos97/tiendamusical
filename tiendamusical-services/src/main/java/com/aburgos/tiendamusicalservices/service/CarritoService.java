package com.aburgos.tiendamusicalservices.service;

import java.util.List;

import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalentities.entities.Carrito;
import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalentities.entities.Factura;

public interface CarritoService {
	
	public CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDTO artistaAlbumDTO, Carrito carrito, Integer cantidadAlbumSeleccionado);
	
	public float calcularTotal(Carrito carrito);
	
	void eliminarAlbumCarrito(CarritoAlbum carritoAlbum);
	
	public float actualizarCantidadAlbum(CarritoAlbum carritoAlbum, Carrito carrito);
	
	/***
	 * Metodo que permite actualizar los registros de los productos comprados por el cliente
	 *  agregandolos al carrito de compra y actualizando su estasus a PAGADO.
	 * @param carritoAlbums {@link List} lista de productos a actualizar
	 * @param factura {@link Factura} Objeto con la factura y la orden de compra.
	 * @return {@link boolean} estatus de actualizacion del carrito
	 */
	public boolean actualizarCarritoAlbum(List<CarritoAlbum> carritoAlbums, Factura factura);

}
