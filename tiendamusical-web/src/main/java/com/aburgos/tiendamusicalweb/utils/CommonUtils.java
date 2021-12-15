/**
 * 
 */
package com.aburgos.tiendamusicalweb.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @author DevPredator
 * Clase generada para crear funciones globales o comunes entre clases del proyecto.
 */
public class CommonUtils {
	/**
	 * Metodo que permite mostrar un mensaje al usuario.
	 * @param severity {@link Severity} objeto que indica el tipo de mensaje a mostrar.
	 * @param summary {@link String} titulo del mensaje.
	 * @param detail {@link String} detalle del mensaje.
	 */
	public static void mostrarMensaje(Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public static void redireccionar(String url) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String contextPath = externalContext.getRequestContextPath();
		externalContext.redirect(contextPath + url);
	}
	
	/**
	 * Metodo que permite guardar imagenes en un directorio.
	 * @param path {@link String} directorio absoluto.
	 * @param nombreArchivo {@link String} nombre del archivo.
	 * @param inputStream {@link InputStream} contenido del archivo en flujos de bytes.
	 * @throws IOException {@link IOException} excepcion en caso de error al copiar y agregar la imagen del album.
	 */
	public static void guardarImagen(String path, String nombreArchivo, InputStream inputStream) throws IOException {
		Files.copy(inputStream, new File(path, nombreArchivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
}
