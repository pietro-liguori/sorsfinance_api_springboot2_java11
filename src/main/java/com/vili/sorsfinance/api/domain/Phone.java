package com.vili.sorsfinance.api.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.PhoneType;
import com.vili.sorsfinance.api.repositories.PhoneRepository;
import com.vili.sorsfinance.api.services.PhoneService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(value = PhoneService.class)
@RepositoryRef(value = PhoneRepository.class)
@JsonPropertyOrder({ "id", "number", "type", "preferred", "contact" })
public class Phone extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean preferred;
	
	@ManyToOne
	@FilterSetting(alias = "owner", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "addresses", "phones", "emails", "preferredContact" })
	private Contact contact;
	
	public Phone() {
		super();
	}

	public Phone(Long id) {
		super(id, Phone.class);
	}

	public Phone(Long id, String number, PhoneType type, Boolean preferred) {
		super(id, Phone.class);
		this.number = number;
		this.type = type.getCode();
		this.preferred = preferred;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return PhoneType.toEnum(type).getLabel();
	}

	public void setType(PhoneType type) {
		this.type = type.getCode();
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
