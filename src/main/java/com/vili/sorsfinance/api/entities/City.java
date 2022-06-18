package com.vili.sorsfinance.api.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "name", "state" })
public class City extends BusEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	@ManyToOne
	@JoinColumn(name = "state_id")
	@JsonIgnoreProperties({ "cities" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
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
