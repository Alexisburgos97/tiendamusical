package com.aburgos.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.ArtistaDAO;
import com.aburgos.tiendamusicaldata.dao.NacionalidadDAO;
import com.aburgos.tiendamusicaldata.dao.SubGeneroDAO;
import com.aburgos.tiendamusicaldata.dao.GeneroDAO;
import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Genero;
import com.aburgos.tiendamusicalentities.entities.Nacionalidad;
import com.aburgos.tiendamusicalentities.entities.SubGenero;
import com.aburgos.tiendamusicalservices.service.AdminArtistasService;

@Service
public class AdminArtistasServiceImpl implements AdminArtistasService {

	@Autowired
	private ArtistaDAO artistaDAO;
	
	@Autowired
	private NacionalidadDAO nacionalidadDAO;
	
	@Autowired
	private GeneroDAO GeneroDAO;
	
	@Autowired
	private SubGeneroDAO subGeneroDAO;
	
	
	@Override
	public List<Artista> consultarArtista() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("nombre"));
		Page<Artista> page = this.artistaDAO.findAll(pageable);
		return page.getContent();
	}


	@Override
	public List<Nacionalidad> consultarNacionalidades() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("descripcion"));
		Page<Nacionalidad> page = this.nacionalidadDAO.findAll(pageable);
		return page.getContent();
	}


	@Override
	public List<Genero> consultarGeneros() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("descripcion"));
		Page<Genero> page = this.GeneroDAO.findAll(pageable);
		return page.getContent();
	}


	@Override
	public List<SubGenero> consultarSubgeneroByGenero(Long idGenero) {
		return this.subGeneroDAO.findByGenero(idGenero);
	}


	@Override
	public Artista guardarArtista(Artista artista) {
		
		if(artista.getIdArtista() != null) {
			artista.setFechaActualizacion(LocalDateTime.now());
		}else {
			artista.setFechaCreacion(LocalDateTime.now());
			artista.setEstatus(true);
		}
		
		return this.artistaDAO.save(artista);
		
	}

}
