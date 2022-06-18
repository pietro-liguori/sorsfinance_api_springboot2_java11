package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.EmailDTO;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "email", "preferred", "contact" })
public class Email extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String email;
	private Boolean preferred;
	@ManyToOne
	@JsonIgnoreProperties({ "addresses", "phones", "emails", "preferredContact" })
	private Contact contact;

	public Email() {
		super();
	}

	public Email(Long id) {
		super(id, Email.class);
	}

	public Email(String email) {
		super();
		this.email = email;
	}

	public Email(Long id, String email, Boolean preferred) {
		super(id, Email.class);
		this.email = email;
		this.preferred = preferred;
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

	public Email setContact(Contact contact) {
		this.contact = contact;
		return this;
	}

	public static Email fromDTO(EmailDTO dto) {
		Email email = new Email(dto.getId(), dto.getEmail(), dto.getPreferred());
		Contact contact = new Contact(dto.getContactId());
		email.setContact(contact);
		email.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		return email;
	}
}
