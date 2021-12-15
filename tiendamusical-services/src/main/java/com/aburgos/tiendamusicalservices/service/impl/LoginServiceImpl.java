/**
 * 
 */
package com.aburgos.tiendamusicalservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicalservices.service.LoginService;
import com.aburgos.tiendamusicaldata.dao.PersonaDAO;
import com.aburgos.tiendamusicalentities.entities.Persona;

/**
 * @author aburgos
 * Clase que implementa las funciones para la logica de negocio para la pantalla de login.
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PersonaDAO personaDAOImpl;
	
	@Override
	public Persona consultarUsuarioLogin(String usuario, String password) {
		return this.personaDAOImpl.findByUsuarioAndPassword(usuario, password);
	}

}
