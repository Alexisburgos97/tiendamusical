/**
 * 
 */
package com.devpredator.tiendamusicaldata.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.devpredator.tiendamusicalentities.entities.Persona;

/**
 * @author DevPredator
 * Clase DAO que realiza el CRUD con SpringJPA para la tabla de persona.
 */
public interface PersonaDAO extends PagingAndSortingRepository<Persona, Long>{
	/**
	 * Metodo que permite consultar el usuario que trata de ingresar a sesion.
	 * @param usuario {@link String} usuario capturado por la persona.
	 * @param password {@link String} contraseña capturada por la persona. 
	 * @return {@link Persona} objeto con la persona encontrada.
	 */
	@Query("SELECT p FROM Persona p WHERE p.usuario = ?1 AND p.password = ?2")
	Persona findByUsuarioAndPassword(String usuario, String password);
}
