package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.repositories.StateRepository;
import com.vili.sorsfinance.api.services.StateService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(value = StateService.class)
@RepositoryRef(value = StateRepository.class)
@JsonPropertyOrder({ "id", "name", "acronym", "country", "cities" })
public class State extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String acronym;
	
	@OneToMany(mappedBy = "state")
	@FilterSetting(alias = "city", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "state" })
	private Set<City> cities = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "states" })
	private Country country;
	
	public State() {
		super();
	}

	public State(Long id) {
		super(id, State.class);
	}

	public State(Long id, String name, String acronym, Country country) {
		super(id, State.class);
		this.name = name;
		this.acronym = acronym;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void addCity(City city) {
		cities.add(city);
	}
	
	public void addCities(City... cities) {
		for (City x : cities) {
			this.cities.add(x);
		}
	}
}