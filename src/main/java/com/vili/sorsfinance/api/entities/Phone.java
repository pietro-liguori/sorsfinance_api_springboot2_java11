package com.vili.sorsfinance.api.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.PhoneType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "number", "type", "preferred", "contact" })
public class Phone extends BusEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String number;
	private Integer type;
	private Boolean preferred;
	@ManyToOne
	@JsonIgnoreProperties({ "addresses", "phones", "emails", "preferredContact" })
	private Contact contact;
	
	public Phone() {
		super();
	}

	public Phone(Long id, String number, PhoneType type, Boolean preferred, Contact contact) {
		super(id, Phone.class);
		this.number = number;
		this.type = type.getCode();
		this.preferred = preferred;
		this.contact = contact;
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
