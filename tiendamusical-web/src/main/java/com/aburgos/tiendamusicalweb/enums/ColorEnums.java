package com.aburgos.tiendamusicalweb.enums;

public enum ColorEnums {
	
	COLOR_ROJO(1, "rgba(255,99,132,0.2)"),
			
	COLOR_AMARILLO(2, "rgba(255,255,0,0.2)"),

	COLOR_VERDE(3, "rgba(52,255,51,0.2)"),
			
	COLOR_MORADO(4, "rgba(87,35,100,1.0)"),
					
	COLOR_AZUL(5, "rgba(51,59,255,0.2)");
	
	private int color;
	
	private String descripcion;

	private ColorEnums(int color, String descripcion) {
		this.color = color;
		this.descripcion = descripcion;
	}

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
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
