package com.aburgos.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.CarritoAlbumDAO;
import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalentities.entities.Carrito;
import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalentities.entities.Factura;
import com.aburgos.tiendamusicalservices.service.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {
	
	@Autowired
	private CarritoAlbumDAO carritoAlbumDAO;
	
	
	@Override
	public CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDTO artistaAlbumDTO, Carrito carrito,Integer cantidadAlbumSeleccionado) {
		
		CarritoAlbum carritoAlbum = new CarritoAlbum();
		
		carritoAlbum.setAlbum(artistaAlbumDTO.getAlbum());
		carritoAlbum.setCarrito(carrito);
		carritoAlbum.setCantidad(cantidadAlbumSeleccionado);
		carritoAlbum.setEstatus("PENDIENTE");
		
		this.carritoAlbumDAO.save(carritoAlbum);
		
		return carritoAlbum;
	}

	@Override
	public float calcularTotal(Carrito carrito) {
		
		float total = 0.0F;

		for(CarritoAlbum carritoAlb : carrito.getCarritoAlbum() ) {
			
			total += (carritoAlb.getAlbum().getValor() * carritoAlb.getCantidad());
			
		}
		
		return total;
		
	}

	@Override
	public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
		this.carritoAlbumDAO.delete(carritoAlbum);		
	}

	@Override
	public float actualizarCantidadAlbum(CarritoAlbum carritoAlbum, Carrito carrito) {
		
		this.carritoAlbumDAO.save(carritoAlbum);
		
		return this.calcularTotal(carrito);
	}

	@Override
	public boolean actualizarCarritoAlbum(List<CarritoAlbum> carritoAlbums, Factura factura) {

		boolean actualizados = false;
		
		for(CarritoAlbum carritoAlbum : carritoAlbums) {
			carritoAlbum.setEstatus("PAGADO");
			carritoAlbum.setFechaCompra(LocalDateTime.now());
			carritoAlbum.setFactura(factura);
		}
		
		Iterable<CarritoAlbum> carritosActualizados = this.carritoAlbumDAO.saveAll(carritoAlbums);
		
		if(carritosActualizados != null) {
			actualizados = true;
		}
		
		return actualizados;
		
	}

}
