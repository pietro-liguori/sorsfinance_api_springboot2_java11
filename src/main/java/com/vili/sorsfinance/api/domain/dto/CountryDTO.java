package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.validation.constraints.ValidCountry;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidCountry
public class CountryDTO extends DataTransferObject {

	private String name;
	private String acronym;
	
	public CountryDTO() {
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
	
	@Override
	public Country toEntity() {
		return new Country(getId(), getName(),getAcronym());
	}
}
