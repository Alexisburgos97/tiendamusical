package com.aburgos.tiendamusicalweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.aburgos.tiendamusicalentities.entities.Artista;
import com.aburgos.tiendamusicalentities.entities.Rol;
import com.aburgos.tiendamusicalservices.service.AdminPerfilesService;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

/**
 * @author Aburgos
 * Clase que controla el flujo de la pantalla de artistas para el administrador.
 */
@ManagedBean
@ViewScoped
public class AdminPerfilesController {
	
	/**
	 * Objeto que permite mostrar los mensajes de LOG en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdminPerfilesController.class);
	
	/**
	 * perfiles a mostrarse en el datatable.
	 */
	private List<Rol> perfiles;
	
	/**
	 * perfiles filtrados por los encabezados del datatable.
	 */
	private List<Rol> perfilesFiltrados;

	/**
	 * Objeto que guarda o actualiza un perfil.
	 */
	private Rol perfil;
	
	/**
	 * Bean de Spring inyectado con JSF para ocupar los metodos de logica de negocio para perfiles.
	 */
	@ManagedProperty("#{adminPerfilesServiceImpl}")	
	private AdminPerfilesService adminPerfilesServiceImpl;
	
	/**
	 * Inicializando la pantalla de perfiles.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de perfiles del administrador...");
		this.consultar();
		this.limpiarComponentes();
	}
	/**
	 * Permite consultar la informacion de los artistas de la base de datos.
	 */
	public void consultar() {
		this.perfiles = this.adminPerfilesServiceImpl.consultarPerfiles();
		
	}
	
	/**
	 * Metodo que permite inicializar o limpiar los componentes u objetos utilizados en el formulario.
	 */
	public void limpiarComponentes() {
		
		this.perfil = new Rol();
		
	}
	
	/**
	 * Metodo que permite guardar el perfil.
	 */
	public void guardarPerfil() {
		
		Rol perfilGuardado = this.adminPerfilesServiceImpl.guardarPerfil(this.perfil);
		
		if (perfilGuardado.getIdRol() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Perfil " + this.perfil.getNombre() + " guardado exitósamente");
			
			PrimeFaces.current().executeScript("PF('dlgPerfiles').hide()");
			
			this.limpiarComponentes();
		}else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un error al guardar el perfil, favor de contactar con soporte.");
		}
		
		this.consultar();		
	}
	/**
	 * Metodo que permite precargar el artista seleccionado para actualizar.
	 * @param artistaSeleccionado {@link Artista} artista seleccionado a actualizar.
	 */
	public void cargarPerfil(Rol rolSeleccionado) {
		this.perfil = rolSeleccionado;
	}
	/**
	 * @return the perfiles
	 */
	public List<Rol> getPerfiles() {
		return perfiles;
	}
	/**
	 * @param perfiles the perfiles to set
	 */
	public void setPerfiles(List<Rol> perfiles) {
		this.perfiles = perfiles;
	}
	/**
	 * @return the perfilesFiltrados
	 */
	public List<Rol> getPerfilesFiltrados() {
		return perfilesFiltrados;
	}
	/**
	 * @param perfilesFiltrados the perfilesFiltrados to set
	 */
	public void setPerfilesFiltrados(List<Rol> perfilesFiltrados) {
		this.perfilesFiltrados = perfilesFiltrados;
	}
	/**
	 * @return the perfil
	 */
	public Rol getPerfil() {
		return perfil;
	}
	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Rol perfil) {
		this.perfil = perfil;
	}
	/**
	 * @return the adminPerfilesServiceImpl
	 */
	public AdminPerfilesService getAdminPerfilesServiceImpl() {
		return adminPerfilesServiceImpl;
	}
	/**
	 * @param adminPerfilesServiceImpl the adminPerfilesServiceImpl to set
	 */
	public void setAdminPerfilesServiceImpl(AdminPerfilesService adminPerfilesServiceImpl) {
		this.adminPerfilesServiceImpl = adminPerfilesServiceImpl;
	}

	
	
}
	