package com.aburgos.tiendamusicaldata.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.aburgos.tiendamusicalentities.entities.Factura;
import com.aburgos.tiendamusicalentities.entities.Persona;

public interface FacturaDAO extends PagingAndSortingRepository<Factura, Long> {
	
	/***
	 * Metodo que permite consultar la lista de facturas de la persona que ha realizado compras.
	 * @param persona
	 * @return
	 */
	List<Factura> findAllByPersona(Persona persona);

}
