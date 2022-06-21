package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.StateDTO;
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

	public State setName(String name) {
		this.name = name;
		return this;
	}

	public String getAcronym() {
		return acronym;
	}

	public State setAcronym(String acronym) {
		this.acronym = acronym;
		return this;
	}

	public Country getCountry() {
		return country;
	}

	public State setCountry(Country country) {
		this.country = country;
		return this;
	}

	public Set<City> getCities() {
		return cities;
	}

	public State addCity(City city) {
		cities.add(city);
		return this;
	}
	
	public State addCities(City... cities) {
		for (City x : cities) {
			this.cities.add(x);
		}
		return this;
	}
	
	public static State fromDTO(StateDTO dto) {
		State state = new State(dto.getId(), dto.getName(), dto.getAcronym(), new Country(dto.getCountryId()));
		state.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		return state;
	}
}