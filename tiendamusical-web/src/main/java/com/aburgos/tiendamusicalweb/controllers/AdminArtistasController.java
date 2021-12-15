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

import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Genero;
import com.aburgos.tiendamusicalentities.entities.Nacionalidad;
import com.aburgos.tiendamusicalentities.entities.SubGenero;
import com.aburgos.tiendamusicalservices.service.AdminArtistasService;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

/**
 * @author Aburgos
 * Clase que controla el flujo de la pantalla de artistas para el administrador.
 */
@ManagedBean
@ViewScoped
public class AdminArtistasController {
	/**
	 * Objeto que permite mostrar los mensajes de LOG en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdminArtistasController.class);
	/**
	 * Artistas a mostrarse en el datatable.
	 */
	private List<Artista> artistas;
	/**
	 * Artistas filtrados por los encabezados del datatable.
	 */
	private List<Artista> artistasFiltrados;
	/**
	 * Nacionalidad a seleccionar
	 */
	private List<Nacionalidad> nacionalidades;
	/**
	 * Genero a seleccionar.
	 */
	private List<Genero> generos;
	/**
	 * SubGenero a seleccionar.
	 */
	private List<SubGenero> subGeneros;
	/**
	 * Objeto que guarda o actualiza un artista.
	 */
	private Artista artista;
	/**
	 * Objeto que se utiliza para almacenar el archivo de la imagen del artista a cargar de forma temporal.
	 */
	private UploadedFile uploadedFile;
	/**
	 * Objeto que contendra el flujo de bytes del archivo de imagen a cargar.
	 */
	private InputStream inputStream;
	/**
	 * Directorio donde se almacenan las imagenes de artistas del proyecto.
	 */
	String absolutePath = null;
	
	/**
	 * Bean de Spring inyectado con JSF para ocupar los metodos de logica de negocio para artistas.
	 */
	@ManagedProperty("#{adminArtistasServiceImpl}")	
	private AdminArtistasService adminArtistasServiceImpl;
	/**
	 * Inicializando la pantalla de artistas.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de artistas del administrador...");
		this.consultar();
		this.inicializarServicios();
		this.limpiarComponentes();
	}
	/**
	 * Permite consultar la informacion de los artistas de la base de datos.
	 */
	public void consultar() {
		this.artistas = this.adminArtistasServiceImpl.consultarArtista();
		this.nacionalidades = this.adminArtistasServiceImpl.consultarNacionalidades();
		this.generos = this.adminArtistasServiceImpl.consultarGeneros();
		
	}
	
	public void consultarSubGeneroPorGenero() {
		this.subGeneros = this.adminArtistasServiceImpl.consultarSubgeneroByGenero(this.artista.getSubGenero().getGenero().getIdGenero());
	}
	/**
	 * Metodo que permite inicializar la informacion de los componentes de carga en los desplegables.
	 */
	public void inicializarServicios() {
		//this.disqueras = this.adminAlbumsServiceImpl.consultarDisqueras();
		//this.artistas = this.adminAlbumsServiceImpl.consultarArtistas();
		
		String relativePath = "resources/imagenes/artistas";
		this.absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
	}
	/**
	 * Metodo que permite inicializar o limpiar los componentes u objetos utilizados en el formulario.
	 */
	public void limpiarComponentes() {
		
		this.artista = new Artista();
		this.artista.setNacionalidad(new Nacionalidad());
		this.artista.setSubGenero(new SubGenero());
		this.artista.getSubGenero().setGenero(new Genero());
		
		this.uploadedFile = null;
	}
	/**
	 * Metodo que permite inicializar una imagen de carga temporal para un artista.
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
	 * Metodo que permite guardar el artista.
	 */
	public void guardarArtista() {
		
		try {
			CommonUtils.guardarImagen(this.absolutePath, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un problema al guardar la imagen en el directorio indicado, favor de contactar con soporte.");
			e.printStackTrace();
		}
		
		this.artista.setImagen(this.uploadedFile.getFileName());
		
		Artista artistaGuardado = this.adminArtistasServiceImpl.guardarArtista(this.artista);
		
		if (artistaGuardado.getIdArtista() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Artista " + this.artista.getNombre() + " guardado exitósamente");
			
			PrimeFaces.current().executeScript("PF('dlgArtistas').hide()");
			
			this.limpiarComponentes();
		}else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un error al guardar el artista, favor de contactar con soporte.");
		}
		
		this.consultar();		
	}
	/**
	 * Metodo que permite precargar el artista seleccionado para actualizar.
	 * @param artistaSeleccionado {@link Artista} artista seleccionado a actualizar.
	 */
	public void cargarArtista(Artista artistaSeleccionado) {
		this.subGeneros = this.adminArtistasServiceImpl.consultarSubgeneroByGenero(artistaSeleccionado.getSubGenero().getGenero().getIdGenero());
		this.artista = artistaSeleccionado;
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
	 * @return the artistasFiltrados
	 */
	public List<Artista> getArtistasFiltrados() {
		return artistasFiltrados;
	}
	/**
	 * @param artistasFiltrados the artistasFiltrados to set
	 */
	public void setArtistasFiltrados(List<Artista> artistasFiltrados) {
		this.artistasFiltrados = artistasFiltrados;
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
	 * @return the adminArtistasServiceImpl
	 */
	public AdminArtistasService getAdminArtistasServiceImpl() {
		return adminArtistasServiceImpl;
	}
	/**
	 * @param adminArtistasServiceImpl the adminArtistasServiceImpl to set
	 */
	public void setAdminArtistasServiceImpl(AdminArtistasService adminArtistasServiceImpl) {
		this.adminArtistasServiceImpl = adminArtistasServiceImpl;
	}
	/**
	 * @return the nacionalidades
	 */
	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}
	/**
	 * @param nacionalidades the nacionalidades to set
	 */
	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}
	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}
	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	/**
	 * @return the subGeneros
	 */
	public List<SubGenero> getSubGeneros() {
		return subGeneros;
	}
	/**
	 * @param subGeneros the subGeneros to set
	 */
	public void setSubGeneros(List<SubGenero> subGeneros) {
		this.subGeneros = subGeneros;
	}
	
	
	
}
	