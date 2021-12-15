/**
 * 
 */
package com.aburgos.tiendamusicalservices.service;

import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicalentities.entities.Persona;

/**
 * @author aburgos
 * Interface que realiza la logica de negocio para el inicio de sesion de la persona.
 */
@Service
public interface LoginService {
	/**
	 * Metodo que permite consultar un usuario que trata de ingresar a sesion en la tienda musical.
	 * @param usuario {@link String} usuario capturado por la persona.
	 * @param password {@link String} contraseña capturada por la persona.
	 * @return {@link Persona} usuario encontrado en la base de datos.
	 */
	Persona consultarUsuarioLogin(String usuario, String password);
}
