package com.aburgos.tiendamusicalservices.service;

import java.util.List;

import com.aburgos.tiendamusicalentities.entities.Album;
import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Disquera;

public interface AdminAlbumsService {
	
	public List<Disquera> consultarDisqueras();
	
	public List<Artista> consultarArtistas();
	
	public Album guardarAlbum(Album album);

}
