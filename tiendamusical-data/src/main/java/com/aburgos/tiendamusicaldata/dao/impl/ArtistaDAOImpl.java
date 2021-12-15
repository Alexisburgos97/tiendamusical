package com.aburgos.tiendamusicaldata.dao.impl;

import java.util.List;

import com.aburgos.tiendamusicaldata.common.CommonDAO;
import com.aburgos.tiendamusicaldata.dao.ArtistaDAO;
import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalentities.entities.Artista;

public class ArtistaDAOImpl extends CommonDAO<Artista, ArtistaDAO> {
	
	public List<ArtistaAlbumDTO> consultarArtistaAlbumPorFiltro(String filtro){
		return this.repository.consultarArtistaAlbumsPorFiltro(filtro);
	}

}
