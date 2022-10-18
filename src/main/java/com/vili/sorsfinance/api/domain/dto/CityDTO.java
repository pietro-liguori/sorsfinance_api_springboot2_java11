package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.validation.constraints.ValidCity;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidCity
public class CityDTO extends DataTransferObject {

	private String name;
	private Long stateId;
	
	public CityDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public City toEntity() {
		return new City(getId(), getName(), new State(getStateId()));
	}
}
