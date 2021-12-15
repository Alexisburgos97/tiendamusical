package com.aburgos.tiendamusicalservices.service;

import java.util.List;

import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Genero;
import com.aburgos.tiendamusicalentities.entities.Nacionalidad;
import com.aburgos.tiendamusicalentities.entities.SubGenero;

public interface AdminArtistasService {
	
	public List<Artista> consultarArtista();
	
	public List<Nacionalidad> consultarNacionalidades();
	
	public List<Genero> consultarGeneros();
	
	public List<SubGenero> consultarSubgeneroByGenero(Long idGenero);
	
	public Artista guardarArtista(Artista artista);

}
