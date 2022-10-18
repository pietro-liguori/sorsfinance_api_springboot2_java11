package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.validation.constraints.ValidState;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidState
public class StateDTO extends DataTransferObject {

	private String name;
	private String acronym;
	private Long countryId;
	
	public StateDTO() {
		super();
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
		this.acronym = acronym.toUpperCase();
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long stateId) {
		this.countryId = stateId;
	}
	
	@Override
	public State toEntity() {
		return new State(getId(), getName(), getAcronym(), new Country(getCountryId()));
	}
}
