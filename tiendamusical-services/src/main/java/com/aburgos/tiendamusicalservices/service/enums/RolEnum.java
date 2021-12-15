package com.aburgos.tiendamusicalservices.service.enums;

/***
 * Enumeración para los perfiles del sistema  
 * @author Alexis
 *
 */
public enum RolEnum {
	
	CLIENTE(2, "CLIENTE"),
	ADMINISTRADOR(1, "ADMINISTRADOR");
	
	private long clave;
	
	private String descripcion;

	private RolEnum(long clave, String descripcion) {
		this.clave = clave;
		this.descripcion = descripcion;
	}

	/**
	 * @return the clave
	 */
	public long getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(long clave) {
		this.clave = clave;
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
	
	
}
