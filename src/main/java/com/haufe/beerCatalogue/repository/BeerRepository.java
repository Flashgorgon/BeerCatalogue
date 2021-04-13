package com.haufe.beerCatalogue.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.haufe.beerCatalogue.entity.Beer;


public interface BeerRepository extends PagingAndSortingRepository<Beer, Long> {
	
	public List<Beer> findByName(@Param("name") String name);
	List<Beer> findByGraduation(@Param("graduation") Integer graduation);
	List<Beer> findByType(@Param("type") String type);
	List<Beer> findByDescription(@Param("description") String description);
	List<Beer> findByManufacturer_Name(@Param("name") String name);
	List<Beer> findByManufacturer_Nationality(@Param("Nationality") String nationality);
}
