package com.haufe.beerCatalogue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.haufe.beerCatalogue.entity.Beer;
import com.haufe.beerCatalogue.entity.Manufacturer;
import com.haufe.beerCatalogue.repository.BeerRepository;
import com.haufe.beerCatalogue.repository.ManufacturerRepository;


@SpringBootApplication
public class BeerCatalogueApplication {

	@Autowired BeerRepository beerRepository;
	@Autowired ManufacturerRepository manuRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogueApplication.class, args);
	}
	
	public @PostConstruct void init() {
		
		Manufacturer damm = new Manufacturer();
		damm.setName("Damm");
		damm.setNationality("Spain");
		manuRepository.save(damm);
		
		Beer beer = new Beer();
		beer.setName("Xiveca");
		beer.setGraduation(4); 
		beer.setType("Lager");
		beer.setDescription("Soft taste and light body");
		beer.setManufacturer(damm);
		beerRepository.save(beer);
		
		Beer beer1 = new Beer();
		beer1.setName("Voll Damm");
		beer1.setGraduation(8); 
		beer1.setType("Lager");
		beer1.setDescription("Strong taste and intense body and flavour");
		beer1.setManufacturer(damm);
		beerRepository.save(beer1);
		
		Manufacturer heineken = new Manufacturer();
		heineken.setName("Heineken");
		heineken.setNationality("Holland");
		manuRepository.save(heineken);
		
		Beer beer2 = new Beer();
		beer2.setName("One");
		beer2.setGraduation(6); 
		beer2.setType("Lager");
		beer2.setDescription("Soft and floral taste");
		beer2.setManufacturer(heineken);
		beerRepository.save(beer2);
		
		Manufacturer corona = new Manufacturer();
		corona.setName("Corona");
		corona.setNationality("Mexico");
		manuRepository.save(corona);
		
		Beer beer3 = new Beer();
		beer3.setName("Coronitas");
		beer3.setGraduation(5); 
		beer3.setType("Lager");
		beer3.setDescription("Soft and light taste");
		beer3.setManufacturer(corona);
		beerRepository.save(beer3);
		
		Manufacturer btown = new Manufacturer();
		btown.setName("Beawer Town");
		btown.setNationality("England");
		manuRepository.save(btown);
		
		Beer beer4 = new Beer();
		beer4.setName("Gamma Ray");
		beer4.setGraduation(5); 
		beer4.setType("APA");
		beer4.setDescription("Juicy and tropical aromas");
		beer4.setManufacturer(btown);
		beerRepository.save(beer4);
		
	}

}
