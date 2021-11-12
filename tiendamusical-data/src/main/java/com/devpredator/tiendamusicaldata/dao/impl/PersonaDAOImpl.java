/**
 * 
 */
package com.devpredator.tiendamusicaldata.dao.impl;

import com.devpredator.tiendamusicaldata.common.CommonDAO;
import com.devpredator.tiendamusicaldata.dao.PersonaDAO;
import com.devpredator.tiendamusicalentities.entities.Persona;

/**
 * @author DevPredator
 * Clase que implementa el CRUD Generico y las funciones de la interface de PersonaDAO.
 */
public class PersonaDAOImpl extends CommonDAO<Persona, PersonaDAO> {
	/**
	 * Metodo permite consultar una persona por su usuario y contraseña.
	 * @param usuario {@link String} usuario capturado por la persona.
	 * @param password {@link String} contraseña capturada por la persona. 
	 * @return {@link Persona} persona encontrada.
	 */
	public Persona findUsuarioAndPassword(String usuario, String password) {
		return this.repository.findByUsuarioAndPassword(usuario, password);
	}
}
