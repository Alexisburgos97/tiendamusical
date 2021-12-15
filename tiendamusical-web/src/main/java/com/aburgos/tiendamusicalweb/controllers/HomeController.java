package com.aburgos.tiendamusicalweb.controllers;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.aburgos.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.aburgos.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.aburgos.tiendamusicalentities.entities.CarritoAlbum;
import com.aburgos.tiendamusicalservices.service.AlbumService;
import com.aburgos.tiendamusicalservices.service.CarritoService;
import com.aburgos.tiendamusicalservices.service.HomeServices;
import com.aburgos.tiendamusicalweb.enums.ColorEnums;
import com.aburgos.tiendamusicalweb.session.SessionBean;
import com.aburgos.tiendamusicalweb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class HomeController {
	
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
	
	private String filtro;
	
	private List<ArtistaAlbumDTO> artistasAlbumDTO;
	
	@ManagedProperty("#{homeServicesImpl}")
	private HomeServices homeServicesImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	@ManagedProperty("#{albumServiceImpl}")
	private AlbumService albumServiceImpl;
	
	private BarChartModel barChartModel;
	
	
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		LOGGER.warn("WARN");
		LOGGER.error("ERROR");
		LOGGER.fatal("FATAL");
		
		if(sessionBean.getPersona().getRol().getIdRol() == 1) {
			this.crearGraficaTopTenAlbumsVendidos();
		}
		
	}
	
	
	public void consultarAlbumsArtistaPorFiltro() {
		this.artistasAlbumDTO = this.homeServicesImpl.consultaAlbumsFiltro(this.filtro);
		
		if(this.artistasAlbumDTO != null) {
			this.artistasAlbumDTO.forEach(artistaAlbumDTO -> {
				LOGGER.info("Artista: " + artistaAlbumDTO.getArtista().getNombre());
			});
		}
	}
	
	public void verDetalleAlbum(ArtistaAlbumDTO artistaAlbumDTO) {
		this.sessionBean.setArtistaAlbumDTO(artistaAlbumDTO);
		
		try {
			CommonUtils.redireccionar("/pages/cliente/detalle.xhtml");
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "¡UPS!", "Hubo un error al ver el detalle del producto, contactar a soporte.");
			e.printStackTrace();
		}
		
	}
	
	/***
	 * Metodo que permite agregar un album en el carrito de compra
	 * @param artistaAlbumDTO
	 */
	public void agregarAlbumCarrito(ArtistaAlbumDTO artistaAlbumDTO) { 
		LOGGER.info("Agregando album..." + artistaAlbumDTO.getAlbum().getNombre());
		
		CarritoAlbum carritoAlbum = this.carritoServiceImpl.guardarAlbumsCarrito(artistaAlbumDTO, this.sessionBean.getPersona().getCarrito(), 1);
		
		this.sessionBean.getPersona().getCarrito().getCarritoAlbum().add(carritoAlbum);
	}
	
	/***
	 * Metodo que permite generar y mostrar la gráfica del top ten de albums mas vendidos para el administrador
	 */
	public void crearGraficaTopTenAlbumsVendidos() {
		
		this.barChartModel = new BarChartModel();
		
		ChartData chartData = new ChartData();
		
		//Se consulta la información de los albums mas vendidos
		List<AlbumTopTenDTO> albumTopTen = this.albumServiceImpl.consultarAlbumsTopTen();
		
		String[] meses = new DateFormatSymbols().getMonths();
		
		//Se itera la lista de albums top ten y se integran los valores a la gráfica
		for (int i = 0; i < albumTopTen.size() ; i++) {
			
			BarChartDataSet barChartDataSet = new BarChartDataSet();
			barChartDataSet.setLabel(albumTopTen.get(i).getCarritoAlbum().getAlbum().getNombre());
			barChartDataSet.setBackgroundColor(ColorEnums.values()[i].getDescripcion());
			barChartDataSet.setBorderWidth(1);
			
			List<Number> numeros = new ArrayList<Number>();
			
			//Se obtiene el mes en que se realizo la compra del album
			String mesCompra = albumTopTen.get(i).getCarritoAlbum().getFechaCompra().getMonth().getDisplayName(TextStyle.FULL, new Locale("es","AR"));
			
			for (int j = 0; j < meses.length; j++) {
				
				String mes = meses[j];
				
				if (mes.equals(mesCompra)) {
					numeros.add(albumTopTen.get(i).getCantidadSuma());
				} else {
					numeros.add(0);
				}
				
			}
			
			barChartDataSet.setData(numeros);
			chartData.addChartDataSet(barChartDataSet);
			
		}
		
		List<String> etiquetasMeses = new ArrayList<String>();
		etiquetasMeses.add("Enero");
		etiquetasMeses.add("Febrero");
		etiquetasMeses.add("Marzo");
		etiquetasMeses.add("Abril");
		etiquetasMeses.add("Mayo");
		etiquetasMeses.add("Junio");
		etiquetasMeses.add("Julio");
		etiquetasMeses.add("Agosto");
		etiquetasMeses.add("Septiembre");
		etiquetasMeses.add("Octubre");
		etiquetasMeses.add("Noviembre");
		etiquetasMeses.add("Diciembre");
		
		chartData.setLabels(etiquetasMeses);		
		this.barChartModel.setData(chartData);
		
		BarChartOptions barChartOptions = new BarChartOptions();
		CartesianScales cartesianScales = new CartesianScales();
		CartesianLinearAxes axes = new CartesianLinearAxes();
		CartesianLinearTicks cartesianLinearTicks = new CartesianLinearTicks();
		
		cartesianLinearTicks.setBeginAtZero(true);
		axes.setTicks(cartesianLinearTicks);
		cartesianScales.addYAxesData(axes);
		barChartOptions.setScales(cartesianScales);
		
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Top albums más vendidos por mes");
		barChartOptions.setTitle(title);
		
		this.barChartModel.setOptions(barChartOptions);
				
	}


	/**
	 * @return the filtro
	 */
	public String getFiltro() {
		return filtro;
	}


	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


	/**
	 * @return the artistaAlbumDTO
	 */
	public List<ArtistaAlbumDTO> getArtistaAlbumDTO() {
		return artistasAlbumDTO;
	}


	/**
	 * @param artistaAlbumDTO the artistaAlbumDTO to set
	 */
	public void setArtistaAlbumDTO(List<ArtistaAlbumDTO> artistaAlbumDTO) {
		this.artistasAlbumDTO = artistaAlbumDTO;
	}


	/**
	 * @return the homeServiceImpl
	 */
	public HomeServices getHomeServicesImpl() {
		return homeServicesImpl;
	}


	/**
	 * @param homeServiceImpl the homeServiceImpl to set
	 */
	public void setHomeServicesImpl(HomeServices homeServicesImpl) {
		this.homeServicesImpl = homeServicesImpl;
	}


	/**
	 * @return the artistasAlbumDTO
	 */
	public List<ArtistaAlbumDTO> getArtistasAlbumDTO() {
		return artistasAlbumDTO;
	}


	/**
	 * @param artistasAlbumDTO the artistasAlbumDTO to set
	 */
	public void setArtistasAlbumDTO(List<ArtistaAlbumDTO> artistasAlbumDTO) {
		this.artistasAlbumDTO = artistasAlbumDTO;
	}


	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}


	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}


	/**
	 * @return the carritoServiceImpl
	 */
	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}


	/**
	 * @param carritoServiceImpl the carritoServiceImpl to set
	 */
	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
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
	 * @return the barChartModel
	 */
	public BarChartModel getBarChartModel() {
		return barChartModel;
	}


	/**
	 * @param barChartModel the barChartModel to set
	 */
	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}
	
	
	

}
