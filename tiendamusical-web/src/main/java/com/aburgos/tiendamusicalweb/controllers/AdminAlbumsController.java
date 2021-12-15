package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.aburgos.tiendamusicalentities.entities.Album;
import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Disquera;
import com.aburgos.tiendamusicalservices.service.AdminAlbumsService;
import com.aburgos.tiendamusicalservices.service.AlbumService;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

/**
 * @author Aburgos
 * Clase que controla el flujo de la pantalla de albums para el administrador.
 */
@ManagedBean
@ViewScoped
public class AdminAlbumsController {
	/**
	 * Objeto que permite mostrar los mensajes de LOG en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdminAlbumsController.class);
	/**
	 * Albums a mostrarse en el datatable.
	 */
	private List<Album> albums;
	/**
	 * Albums filtrados por los encabezados del datatable.
	 */
	private List<Album> albumsFiltrados;
	/**
	 * Artistas a seleccionar
	 */
	private List<Artista> artistas;
	/**
	 * Disqueras a seleccionar.
	 */
	private List<Disquera> disqueras;
	/**
	 * Objeto que guarda o actualiza un album.
	 */
	private Album album;
	/**
	 * Objeto que se utiliza para almacenar el archivo de la imagen del album a cargar de forma temporal.
	 */
	private UploadedFile uploadedFile;
	/**
	 * Objeto que contendra el flujo de bytes del archivo de imagen a cargar.
	 */
	private InputStream inputStream;
	/**
	 * Directorio donde se almacenan las imagenes de albums del proyecto.
	 */
	String absolutePath = null;
	/**
	 * Bean de Spring inyectado con JSF para ocupar los metodos de logica de negocio para albums.
	 */
	@ManagedProperty("#{albumServiceImpl}")
	private AlbumService albumServiceImpl;
	/**
	 * Bean de Spring inyectado con JSF para ocupar los metodos de logica de negocio para albums.
	 */
	@ManagedProperty("#{adminAlbumsServiceImpl}")	
	private AdminAlbumsService adminAlbumsServiceImpl;
	/**
	 * Inicializando la pantalla de albums.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de albums del administrador...");
		this.consultar();
		this.inicializarServicios();
		this.limpiarComponentes();
	}
	/**
	 * Permite consultar la informacion de los albums de la base de datos.
	 */
	public void consultar() {
		LOGGER.info("Consultado el listado de albums...");
		this.albums = this.albumServiceImpl.consultarAlbums();
	}
	/**
	 * Metodo que permite inicializar la informacion de los componentes de carga en los desplegables.
	 */
	public void inicializarServicios() {
		this.disqueras = this.adminAlbumsServiceImpl.consultarDisqueras();
		this.artistas = this.adminAlbumsServiceImpl.consultarArtistas();
		
		String relativePath = "resources/imagenes/albums";
		this.absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
	}
	/**
	 * Metodo que permite inicializar o limpiar los componentes u objetos utilizados en el formulario.
	 */
	public void limpiarComponentes() {
		this.album = new Album();
		this.album.setDisquera(new Disquera());
		this.album.setArtista(new Artista());
		this.uploadedFile = null;
	}
	/**
	 * Metodo que permite inicializar una imagen de carga temporal para un album.
	 * @param fileUploadEvent {@link FileUploadEvent} objeto que carga la imagen de forma temporal.
	 */
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();
		
		try {
			this.inputStream = fileUploadEvent.getFile().getInputStream();
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un problema al cargar el archivo, verifica que no estÃ© corrupto.");
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que permite guardar el album.
	 */
	public void guardarAlbum() {
		try {
			CommonUtils.guardarImagen(this.absolutePath, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un problema al guardar la imagen en el directorio indicado, favor de contactar con soporte.");
			e.printStackTrace();
		}
		
		this.album.setImagen(this.uploadedFile.getFileName());
		
		Album albumGuardado = this.adminAlbumsServiceImpl.guardarAlbum(this.album);
		
		if (albumGuardado.getIdAlbum() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Album " + this.album.getNombre() + " guardado exitósamente");
			
			PrimeFaces.current().executeScript("PF('dlgAlbums').hide()");
			
			this.consultar();		
		}
	}
	/**
	 * Metodo que permite precargar el album seleccionado para actualizar.
	 * @param albumSeleccionado {@link Album} album seleccionado a actualizar.
	 */
	public void cargarAlbum(Album albumSeleccionado) {
		this.album = albumSeleccionado;
	}
	/**
	 * @return the albums
	 */
	public List<Album> getAlbums() {
		return albums;
	}
	/**
	 * @param albums the albums to set
	 */
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	/**
	 * @return the albumsFiltrados
	 */
	public List<Album> getAlbumsFiltrados() {
		return albumsFiltrados;
	}
	/**
	 * @param albumsFiltrados the albumsFiltrados to set
	 */
	public void setAlbumsFiltrados(List<Album> albumsFiltrados) {
		this.albumsFiltrados = albumsFiltrados;
	}
	/**
	 * @return the artistas
	 */
	public List<Artista> getArtistas() {
		return artistas;
	}
	/**
	 * @param artistas the artistas to set
	 */
	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	/**
	 * @return the disqueras
	 */
	public List<Disquera> getDisqueras() {
		return disqueras;
	}
	/**
	 * @param disqueras the disqueras to set
	 */
	public void setDisqueras(List<Disquera> disqueras) {
		this.disqueras = disqueras;
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
	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * @return the absolutePath
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}
	/**
	 * @param absolutePath the absolutePath to set
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	/**
	 * @return the albumServiceImpl
	 */
	public AlbumService getAlbumServiceImpl() {
		return albumServiceImpl;
	}
	/**
	 * @param albumServiceImpl the albumServiceImpl to set
	 */
	public void setAlbumServiceImpl(AlbumService albumServiceImpl) {
		this.albumServiceImpl = albumServiceImpl;
	}
	/**
	 * @return the adminAlbumsServiceImpl
	 */
	public AdminAlbumsService getAdminAlbumsServiceImpl() {
		return adminAlbumsServiceImpl;
	}
	/**
	 * @param adminAlbumsServiceImpl the adminAlbumsServiceImpl to set
	 */
	public void setAdminAlbumsServiceImpl(AdminAlbumsService adminAlbumsServiceImpl) {
		this.adminAlbumsServiceImpl = adminAlbumsServiceImpl;
	}
}
	