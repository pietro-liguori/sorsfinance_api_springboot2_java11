package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.validation.constraints.ValidCity;
import com.vili.sorsfinance.api.validation.constraints.ValidStateId;
import com.vili.sorsfinance.framework.DataTransferObject;

public class CityDTO extends DataTransferObject {

	@ValidCity
	private String name;
	@ValidStateId
	private Long stateId;
	
	public CityDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public CityDTO setName(String name) {
		this.name = name;
		return this;
	}

	public Long getStateId() {
		return stateId;
	}

	public CityDTO setStateId(Long stateId) {
		this.stateId = stateId;
		return this;
	}
	
	@Override
	public City toEntity() {
		return new City(getId(), getName(), new State(getStateId()));
	}
}
