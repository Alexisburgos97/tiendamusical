package com.aburgos.tiendamusicalentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nacionalidad")
public class Nacionalidad extends Common{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idNacionalidad")
	private Long idNacionalidad;
	
	@Column(name="descripcion", length=100, nullable = false)
	private String descripcion;
	
	@Column(name="abreviacion", length=4, nullable = false)
	private String abreviacion;

	/**
	 * @return the idNacionalidad
	 */
	public Long getIdNacionalidad() {
		return idNacionalidad;
	}

	/**
	 * @param idNacionalidad the idNacionalidad to set
	 */
	public void setIdNacionalidad(Long idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
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
	 * @return the abreviacion
	 */
	public String getAbreviacion() {
		return abreviacion;
	}

	/**
	 * @param abreviacion the abreviacion to set
	 */
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}
	
	
	
	
}
