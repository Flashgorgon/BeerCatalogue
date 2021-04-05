package com.haufe.beerCatalogue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private Integer graduation;
	private String type;
	private String description;
	
	@ManyToOne
    @JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;
	
	public Integer getGraduation() {
		return graduation;
	}
	public void setGraduation(Integer graduation) {
		this.graduation = graduation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}


}