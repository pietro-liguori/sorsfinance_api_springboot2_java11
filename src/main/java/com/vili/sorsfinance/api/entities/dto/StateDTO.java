package com.vili.sorsfinance.api.entities.dto;

import javax.validation.constraints.Size;

import com.vili.sorsfinance.api.entities.State;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidCountryId;
import com.vili.sorsfinance.validation.constraints.ValidState;
import com.vili.sorsfinance.validation.constraints.ValidStateAcronym;

public class StateDTO extends DTO<State> {

	@ValidState
	private String name;
	@ValidStateAcronym
	@Size(min = 2, max = 2, message = "Must have 2 characters")
	private String acronym;
	@ValidCountryId
	private Long countryId;
	
	public StateDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public StateDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getAcronym() {
		return acronym;
	}

	public StateDTO setAcronym(String acronym) {
		this.acronym = acronym.toUpperCase();
		return this;
	}

	public Long getCountryId() {
		return countryId;
	}

	public StateDTO setCountryId(Long stateId) {
		this.countryId = stateId;
		return this;
	}
}
