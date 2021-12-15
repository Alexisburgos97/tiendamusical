package com.aburgos.tiendamusicalservices.service;

import java.util.List;

import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;

public interface HomeServices {
	
	public List<ArtistaAlbumDTO> consultaAlbumsFiltro(String filtro);

}
