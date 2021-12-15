package com.aburgos.tiendamusicaldata.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.aburgos.tiendamusicalentities.entities.Genero;

public interface GeneroDAO extends PagingAndSortingRepository<Genero, Long> {

}
