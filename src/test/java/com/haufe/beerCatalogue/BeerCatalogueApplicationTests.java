package com.haufe.beerCatalogue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.haufe.beerCatalogue.entity.Beer;
import com.haufe.beerCatalogue.repository.BeerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BeerCatalogueApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BeerRepository beerRepository;
	

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		beerRepository.deleteAll();
	}
	
	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$._links.beers").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(post("/manufacturers")
				.content("{\"name\": \"Damm\", \"nationality\":\"spanish\"}"))
				.andExpect(status().isCreated())
				.andReturn();
		
		String manufacturerLocation = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(post("/beers")
				.content("{\"name\": \"Xiveca\", \"graduation\":4,\"type\":\"Lager\",\"description\":\"Blond soft Lager\",\"manufacturer\":\""+manufacturerLocation+"\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString("beers/")));
		
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/beers").content(
				"{\"name\": \"5 estrellas\", \"graduation\":5,\"type\":\"Lager\",\"description\":\"Special for cañas\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		
		mockMvc.perform(get(location)).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("5 estrellas"))
		.andExpect(jsonPath("$.type").value("Lager"));
	}


	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/beers").content(
				"{\"name\": \"5 estrellas\", \"graduation\":7,\"type\":\"Lager\",\"description\":\"Special for cañas\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"name\": \"5 estrellas\", \"graduation\":6,\"type\":\"Lager\",\"description\":\"Special for cañas y tapas\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("5 estrellas"))
			.andExpect(jsonPath("$.graduation").value("6"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/beers").content(
				"{\"name\": \"5 estrellas\", \"graduation\":7,\"type\":\"Lager\",\"description\":\"Special for cañas\"}"))
				.andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(
				patch(location).content("{\"graduation\": 4}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.name").value("5 estrellas")).andExpect(
						jsonPath("$.graduation").value("4"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/beers").content(
				"{\"name\": \"5 estrellas\", \"graduation\":4,\"type\":\"Lager\",\"description\":\"Special for cañas\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
	
	@Test
	public void searchByManufacturerNameReturnsOneBeer() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/manufacturers")
				.content("{\"name\": \"Damm\", \"nationality\":\"spanish\"}"))
				.andExpect(status().isCreated())
				.andReturn();
		
		String manufacturerLocation = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(post("/beers")
				.content("{\"name\": \"Xiveca\", \"graduation\":4,\"type\":\"Lager\",\"description\":\"Blond soft Lager\",\"manufacturer\":\""+manufacturerLocation+"\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString("beers/")));
		
		List<Beer> beerManName = beerRepository.findByManufacturer_Name("Damm");
		assertThat(beerManName).hasSize(1);
	}
	
	@Test
	public void searchByManufacturerNameReturnsZeroBeer() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/manufacturers")
				.content("{\"name\": \"Damm\", \"nationality\":\"spanish\"}"))
				.andExpect(status().isCreated())
				.andReturn();
		
		String manufacturerLocation = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(post("/beers")
				.content("{\"name\": \"Xiveca\", \"graduation\":4,\"type\":\"Lager\",\"description\":\"Blond soft Lager\",\"manufacturer\":\""+manufacturerLocation+"\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString("beers/")));
		
		List<Beer> beerManName = beerRepository.findByManufacturer_Name("Heineken");
		assertThat(beerManName).hasSize(0);
	}
	
}
