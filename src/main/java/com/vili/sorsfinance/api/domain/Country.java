package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.repositories.CountryRepository;
import com.vili.sorsfinance.api.services.CountryService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(value = CountryService.class)
@RepositoryRef(value = CountryRepository.class)
@JsonPropertyOrder({ "id", "name", "acronym", "areaCodes", "states" })
public class Country extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String acronym;
	
	@ElementCollection
	@CollectionTable(name = "areaCodes")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<String> areaCodes = new HashSet<>();
	
	@OneToMany(mappedBy= "country")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
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

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	public List<String> getAreaCodes() {
		return areaCodes.stream().toList();
	}

	public void addAreaCode(String areaCode) {
		areaCodes.add(areaCode);
	}
	
	public void addAreaCodes(String... areaCode) {
		for (String x : areaCode) {
			this.areaCodes.add(x);
		}
	}

	public List<State> getStates() {
		return states.stream().toList();
	}
	
	public void addState(State state) {
		states.add(state);
	}
	
	public void addStates(State... states) {
		for (State x : states) {
			this.states.add(x);
		}
	}
}
