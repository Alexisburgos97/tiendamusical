package com.aburgos.tiendamusicalentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="artista")
public class Artista extends Common{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idArtista")
	private Long idArtista;
	
	@Column(name = "nombre", length=100, nullable = false)
	private String nombre;
	
	@Column(name = "imagen", length=100, nullable = false)
	private String imagen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idNacionalidad")
	private Nacionalidad nacionalidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idSubGenero")
	private SubGenero subGenero;

	/**
	 * @return the idArtista
	 */
	public Long getIdArtista() {
		return idArtista;
	}

	/**
	 * @param idArtista the idArtista to set
	 */
	public void setIdArtista(Long idArtista) {
		this.idArtista = idArtista;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the nacionalidad
	 */
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the subGenero
	 */
	public SubGenero getSubGenero() {
		return subGenero;
	}

	/**
	 * @param subGenero the subGenero to set
	 */
	public void setSubGenero(SubGenero subGenero) {
		this.subGenero = subGenero;
	}
	
	
}
