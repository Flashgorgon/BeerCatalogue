package com.haufe.beerCatalogue.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.haufe.beerCatalogue.entity.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, Long> {
	
	List<Beer> findByName(@Param("name") String name);
}
