package com.haufe.beerCatalogue.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.haufe.beerCatalogue.entity.Manufacturer;

public interface ManufacturerRepository extends PagingAndSortingRepository<Manufacturer, Long> { }