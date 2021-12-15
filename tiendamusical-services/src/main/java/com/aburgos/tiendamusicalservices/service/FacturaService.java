package com.aburgos.tiendamusicalservices.service;

import java.util.List;

import com.aburgos.tiendamusicalentities.entities.Factura;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.paypal.orders.Order;

public interface FacturaService {
	
	public Factura guardarFactura(Factura factura, Order order, Persona persona);

	/***
	 * Metodo que permite consultar las facturas de la persona.
	 * @param persona
	 * @return
	 */
	List<Factura> consultarFacturaPersona(Persona persona);
}
