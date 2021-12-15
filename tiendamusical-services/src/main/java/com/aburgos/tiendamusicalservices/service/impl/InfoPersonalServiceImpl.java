package com.aburgos.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.PersonaDAO;
import com.aburgos.tiendamusicalentities.entities.Persona;
import com.aburgos.tiendamusicalservices.service.InfoPersonalService;

@Service
public class InfoPersonalServiceImpl implements InfoPersonalService {

	@Autowired
	private PersonaDAO personaDAOImpl;
	
	@Override
	public Persona actualizarPersona(Persona persona) {
		persona.setFechaActualizacion(LocalDateTime.now());
		
		return this.personaDAOImpl.save(persona);
	}

}
