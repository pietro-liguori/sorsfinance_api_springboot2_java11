package com.vili.sorsfinance.api.entities.dto;

import com.vili.sorsfinance.api.entities.City;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidCity;
import com.vili.sorsfinance.validation.constraints.ValidStateId;

public class CityDTO extends DTO<City> {

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
}
