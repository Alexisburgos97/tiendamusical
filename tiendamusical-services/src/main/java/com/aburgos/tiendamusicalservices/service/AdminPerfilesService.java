package com.aburgos.tiendamusicalservices.service;

import java.util.List;

import com.aburgos.tiendamusicalentities.entities.Rol;

public interface AdminPerfilesService {
	
	List<Rol> consultarPerfiles();
	
	Rol guardarPerfil(Rol rol);

}
