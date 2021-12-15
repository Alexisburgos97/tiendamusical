package com.aburgos.tiendamusicalservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.ArtistaDAO;
import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalservices.service.HomeServices;

@Service
public class HomeServicesImpl implements HomeServices {
	
	@Autowired
	private ArtistaDAO artistaDAOImpl;

	@Override
	public List<ArtistaAlbumDTO> consultaAlbumsFiltro(String filtro) {
		return this.artistaDAOImpl.consultarArtistaAlbumsPorFiltro(filtro);
	}

}
