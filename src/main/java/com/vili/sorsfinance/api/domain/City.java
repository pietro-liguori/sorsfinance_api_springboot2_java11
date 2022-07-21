package com.vili.sorsfinance.api.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.repositories.CityRepository;
import com.vili.sorsfinance.api.services.CityService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(value = CityService.class)
@RepositoryRef(value = CityRepository.class)
@JsonPropertyOrder({ "id", "name", "state" })
public class City extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "cities" })
	private State state;
	
	public City() {
		super();
	}

	public City(Long id, String name, State state) {
		super(id, City.class);
		this.name = name;
		this.state = state;
	}

	public City(Long id) {
		super(id, City.class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
