package com.haufe.beerCatalogue.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.haufe.beerCatalogue.entity.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, Long> { }
