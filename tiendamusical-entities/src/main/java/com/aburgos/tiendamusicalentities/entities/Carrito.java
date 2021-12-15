package com.aburgos.tiendamusicalentities.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity(name="carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCarrito")
	private Long idCarrito;
	
	@OneToOne
	@JoinColumn(name="idPersona")
	private Persona persona;
	
	@OneToMany(mappedBy="carrito", fetch = FetchType.EAGER)
	private List<CarritoAlbum> carritoAlbum;

	/**
	 * @return the idCarrito
	 */
	public Long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * @param idCarrito the idCarrito to set
	 */
	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the carritoAlbum
	 */
	public List<CarritoAlbum> getCarritoAlbum() {
		return carritoAlbum;
	}

	/**
	 * @param carritoAlbum the carritoAlbum to set
	 */
	public void setCarritoAlbum(List<CarritoAlbum> carritoAlbum) {
		this.carritoAlbum = carritoAlbum;
	}
	
	
}
