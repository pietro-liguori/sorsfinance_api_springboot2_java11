package com.vili.sorsfinance.api.domain.dto;

import javax.validation.constraints.Size;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.validation.constraints.ValidCountryId;
import com.vili.sorsfinance.api.validation.constraints.ValidState;
import com.vili.sorsfinance.api.validation.constraints.ValidStateAcronym;
import com.vili.sorsfinance.framework.DataTransferObject;

public class StateDTO extends DataTransferObject {

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
	
	@Override
	public State toEntity() {
		return new State(getId(), getName(), getAcronym(), new Country(getCountryId()));
	}
}
