package com.vili.sorsfinance.api.domain.dto;

import javax.validation.constraints.Size;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.validation.constraints.ValidCountry;
import com.vili.sorsfinance.api.validation.constraints.ValidCountryAcronym;
import com.vili.sorsfinance.framework.DataTransferObject;

public class CountryDTO extends DataTransferObject {

	@ValidCountry
	private String name;
	@ValidCountryAcronym
	@Size(min = 3, max = 3, message = "Must have 3 characters")
	private String acronym;
	
	public CountryDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public CountryDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getAcronym() {
		return acronym;
	}

	public CountryDTO setAcronym(String acronym) {
		this.acronym = acronym.toUpperCase();
		return this;
	}
	
	@Override
	public Country toEntity() {
		return new Country(getId(), getName(),getAcronym());
	}
}
