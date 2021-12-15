package com.aburgos.tiendamusicaldata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.aburgos.tiendamusicalentities.entities.SubGenero;

public interface SubGeneroDAO extends PagingAndSortingRepository<SubGenero, Long> {
	
	@Query("SELECT sb FROM SubGenero sb WHERE sb.genero.idGenero = :idGenero")
	public List<SubGenero> findByGenero(@Param("idGenero") Long idGenero);

}
