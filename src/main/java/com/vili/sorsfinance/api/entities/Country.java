package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.CountryDTO;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "name", "acronym", "states" })
public class Country extends BusEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String acronym;
	@OneToMany(mappedBy= "country")
	@JsonIgnoreProperties({ "country" })
	private Set<State> states = new HashSet<>();
	
	public Country() {
		super();
	}

	public Country(Long id) {
		super(id, Country.class);
	}

	public Country(Long id, String name, String acronym) {
		super(id, Country.class);
		this.name = name;
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public Country setName(String name) {
		this.name = name;
		return this;
	}

	public String getAcronym() {
		return acronym;
	}

	public Country setAcronym(String acronym) {
		this.acronym = acronym;
		return this;
	}
	
	public Set<State> getStates() {
		return states;
	}
	
	public Country addState(State state) {
		states.add(state);
		return this;
	}
	
	public Country addStates(State... states) {
		for (State x : states) {
			this.states.add(x);
		}
		return this;
	}
	
	public static Country fromDTO(CountryDTO dto) {
		Country country = new Country(dto.getId(), dto.getName(),dto.getAcronym());
		country.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		return country;
	}
}
