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
@Table(name="album")
public class Album extends Common{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAlbum")
	private Long idAlbum;
	
	@Column(name="nombre" , length=100 , nullable=false)
	private String nombre;
	
	@Column(name="imagen" , length=100 , nullable=false)
	private String imagen;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="anio" , nullable=false)
	private int anio;
	
	@Column(name="formato", length=10,  nullable=false)
	private String formato;
	
	@Column(name="valor" , nullable=false)
	private double valor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDisquera")
	private Disquera disquera;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idArtista")
	private Artista artista;
	
	@Column(name = "raiting")
	private Integer raiting;

	/**
	 * @return the idAlbum
	 */
	public Long getIdAlbum() {
		return idAlbum;
	}

	/**
	 * @param idAlbum the idAlbum to set
	 */
	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return the disquera
	 */
	public Disquera getDisquera() {
		return disquera;
	}

	/**
	 * @param disquera the disquera to set
	 */
	public void setDisquera(Disquera disquera) {
		this.disquera = disquera;
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
	 * @return the raiting
	 */
	public int getRaiting() {
		return raiting;
	}

	/**
	 * @param raiting the raiting to set
	 */
	public void setRaiting(int raiting) {
		this.raiting = raiting;
	}
	
	
}
