package com.vili.sorsfinance.api.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.PhoneType;
import com.vili.sorsfinance.api.repositories.PhoneRepository;
import com.vili.sorsfinance.api.services.PhoneService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(value = PhoneService.class)
@RepositoryRef(value = PhoneRepository.class)
@JsonPropertyOrder({ "id", "number", "countryCode", "countryCodes", "type", "preferred", "contact" })
public class Phone extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean preferred;
	
	@OneToOne
	@JoinColumn(name = "country_id")
	@FilterSetting(nesting = { "id" })
	@JsonIgnore
	private Country country;
	
	@ManyToOne
	@FilterSetting(alias = "owner", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "addresses", "phones", "emails" })
	private Contact contact;
	
	public Phone() {
		super();
	}

	public Phone(Long id) {
		super(id, Phone.class);
	}

	public Phone(Long id, String number, Country country, PhoneType type, Boolean preferred) {
		super(id, Phone.class);
		this.country = country;
		this.number = number;
		this.type =  type == null ? null : type.getCode();
		this.preferred = preferred;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type == null ? null : type.getCode();
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getCountryCode() {
		List<String> codes = this.country.getAreaCodes();
		return codes.size() != 1 ? null : codes.get(0);
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<String> getCountryCodes() {
		List<String> codes = this.country.getAreaCodes();
		return codes.size() < 2 ? null : codes;
	}
}
