package com.aburgos.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aburgos.tiendamusicaldata.dao.AlbumDAO;
import com.aburgos.tiendamusicaldata.dao.ArtistaDAO;
import com.aburgos.tiendamusicaldata.dao.DisqueraDAO;
import com.aburgos.tiendamusicalentities.entities.Album;
import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Disquera;
import com.aburgos.tiendamusicalservices.service.AdminAlbumsService;

@Service
public class AdminAlbumsServiceImpl implements AdminAlbumsService {

	@Autowired
	private ArtistaDAO artistaDAOImpl;
	
	@Autowired
	private DisqueraDAO disqueraDAOImpl;
	
	@Autowired
	private AlbumDAO albumDAOImpl;
	
	@Override
	public List<Disquera> consultarDisqueras() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("descripcion"));
		Page<Disquera> page = this.disqueraDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public List<Artista> consultarArtistas() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("nombre"));
		Page<Artista> page = this.artistaDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Album guardarAlbum(Album album) {
		
		if(album.getIdAlbum() != null) {
			album.setFechaCreacion(LocalDateTime.now());
		}else {
			album.setFechaCreacion(LocalDateTime.now());
			album.setEstatus(true);
		}
		
		return this.albumDAOImpl.save(album);
		
	}

}
