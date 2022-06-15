package com.vili.sorsfinance.api.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "email", "preferred", "contact" })
public class Email extends BusEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private Boolean preferred;
	@ManyToOne
	@JsonIgnoreProperties({ "addresses", "phones", "emails", "preferredContact" })
	private Contact contact;
	
	public Email() {
		super();
	}

	public Email(Long id, String email, Boolean preferred, Contact contact) {
		super(id, Email.class);
		this.email = email;
		this.preferred = preferred;
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
