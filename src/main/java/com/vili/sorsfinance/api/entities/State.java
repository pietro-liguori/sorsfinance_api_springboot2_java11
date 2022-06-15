package com.vili.sorsfinance.api.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "name", "acronym", "country", "cities" })
public class State extends BusEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String acronym;
	@JsonIgnoreProperties({ "state" })
	@OneToMany(mappedBy = "state")
	private Set<City> cities = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "country_id")
	@JsonIgnoreProperties({ "states" })
	private Country country;
	
	public State() {
		super();
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