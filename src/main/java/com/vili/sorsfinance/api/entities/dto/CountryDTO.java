package com.vili.sorsfinance.api.entities.dto;

import javax.validation.constraints.Size;

import com.vili.sorsfinance.api.entities.Country;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidCountry;
import com.vili.sorsfinance.validation.constraints.ValidCountryAcronym;

public class CountryDTO extends DTO<Country> {

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
}
