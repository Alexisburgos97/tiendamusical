package com.aburgos.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.FacturaDAO;
import com.aburgos.tiendamusicalentities.entities.Factura;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.aburgos.tiendamusicalservices.service.FacturaService;
import com.paypal.orders.Order;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;
	
	public Factura guardarFactura(Factura factura, Order order, Persona persona) {
		factura.setOrderId(order.id());
		factura.setFechaCreacion(LocalDateTime.now());
		factura.setEstatus(false);
		factura.setPais(order.payer().addressPortable().adminArea1());
		factura.setCiudad(order.payer().addressPortable().adminArea2());
		factura.setCodigoPostal(order.payer().addressPortable().postalCode());
		factura.setDireccion(order.payer().addressPortable().addressLine1() + ", " + order.payer().addressPortable().addressLine2() );
		factura.setDivisa(order.purchaseUnits().get(0).amountWithBreakdown().currencyCode());
		
		double envio = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shipping().value());
		
		factura.setEnvio(envio);
		
		double envioDescuento = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shippingDiscount().value());
		
		factura.setEnvioDescuento(envioDescuento);
		
		double handlign = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().handling().value());
		
		factura.setHandling(handlign);
		
		double total = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().itemTotal().value());
		
		factura.setTotal(total);
		
		double impuestoTotal = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().taxTotal().value());
		
		factura.setImpuestoTotal(impuestoTotal);
		
		factura.setPersona(persona);
		
		return this.facturaDAO.save(factura);
	}

	@Override
	public List<Factura> consultarFacturaPersona(Persona persona) {
		return this.facturaDAO.findAllByPersona(persona);
	}

}
