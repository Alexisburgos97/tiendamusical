package com.aburgos.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.PersonaDAO;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.aburgos.tiendamusicalservices.service.RegistroService;
import com.aburgos.tiendamusicalservices.service.enums.RolEnum;

@Service
public class RegistroServiceImpl implements RegistroService {
	
	@Autowired
	private PersonaDAO personaDAO;

	@Override
	public Persona guardarPersona(Persona persona) {
		
		persona.getRol().setIdRol(RolEnum.CLIENTE.getClave());
		persona.setFechaCreacion(LocalDateTime.now());
		persona.setEstatus(true);
		
		persona.getCarrito().setPersona(persona);
		
		return this.personaDAO.save(persona);
	}

}
