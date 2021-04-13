package com.haufe.beerCatalogue.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haufe.beerCatalogue.entity.Beer;
import com.haufe.beerCatalogue.repository.BeerRepository;

@RestController
@RequestMapping("search")
public class BeerController {
    
	@Autowired
	BeerRepository repository;
	
    @GetMapping("/name/{name}")
    public List<Beer> getBeerByName(@PathVariable String name) {
        return repository.findByName(name);
    }
    
    @GetMapping("/graduation/{id}")
    public List<Beer> getBeerByGraduation(@PathVariable Integer id) {
        return repository.findByGraduation(id);
    }
    
    @GetMapping("/type/{type}")
    public List<Beer> getBeerByType(@PathVariable String type) {
        return repository.findByType(type);
    }
    
    @GetMapping("/description/{description}")
    public List<Beer> getBeerByDescription(@PathVariable String description) {
        return repository.findByDescription(description);
    }
    
    @GetMapping("/manufacturer/{manufacturerName}")
    public List<Beer> getBeerByManufacturerName(@PathVariable String manufacturerName) {
        return repository.findByManufacturer_Name(manufacturerName);
    }

    @GetMapping("/nationality/{manufacturerNationality}")
    public List<Beer> getBeerByManufacturerNationality(@PathVariable String manufacturerNationality) {
        return repository.findByManufacturer_Nationality(manufacturerNationality);
    }
}
