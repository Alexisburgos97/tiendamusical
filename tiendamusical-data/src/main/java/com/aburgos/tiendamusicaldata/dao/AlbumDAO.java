package com.aburgos.tiendamusicaldata.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aburgos.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.aburgos.tiendamusicalentities.entities.Album;

/**
 * @author DevPredator
 * Clase DAO que contiene los metodos del CRUD con SPRING JPA para la tabla de album.
 */
public interface AlbumDAO extends PagingAndSortingRepository<Album, Long> {

	@Query("SELECT new com.aburgos.tiendamusicalentities.dto.AlbumTopTenDTO(ca, SUM(ca.cantidad) as cantidadSuma) "
		 + "FROM CarritoAlbum ca "
		 + "INNER JOIN ca.album a "
		 + "WHERE ca.estatus = 'PAGADO' "
		 + "GROUP BY a.nombre")
	Page<AlbumTopTenDTO> consultarAlbumsTopTen(Pageable pageable);
}
