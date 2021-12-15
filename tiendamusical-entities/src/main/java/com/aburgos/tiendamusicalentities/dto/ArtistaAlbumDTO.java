package com.aburgos.tiendamusicalentities.dto;

import com.aburgos.tiendamusicalentities.entities.Album;
import com.aburgos.tiendamusicalentities.entities.Artista;

public class ArtistaAlbumDTO {

	Artista artista;
	
	Album album;
	
	public ArtistaAlbumDTO() {
		super();
	}

	public ArtistaAlbumDTO(Album album,Artista artista ) {
		this.artista = artista;
		this.album = album;
	}

	/**
	 * @return the artista
	 */
	public Artista getArtista() {
		return artista;
	}

	/**
	 * @param artista the artista to set
	 */
	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
}
