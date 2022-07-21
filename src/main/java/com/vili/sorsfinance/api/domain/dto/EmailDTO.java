package com.vili.sorsfinance.api.domain.dto;

import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.api.validation.constraints.ValidContactId;
import com.vili.sorsfinance.api.validation.constraints.ValidEmail;
import com.vili.sorsfinance.framework.DataTransferObject;

public class EmailDTO extends DataTransferObject {

	@ValidEmail
	private String email;
	@NotNull(message = "Must not be null")
	private Boolean preferred;
	@ValidContactId
	private Long contactId;

	public EmailDTO() {
		super();
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public EmailDTO setPreferred(Boolean preferred) {
		this.preferred = preferred;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public EmailDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public Long getContactId() {
		return contactId;
	}

	public EmailDTO setContactId(Long contactId) {
		this.contactId = contactId;
		return this;
	}
	
	@Override
	public Email toEntity() {
		Email email = new Email(getId(), getEmail(), getPreferred());
		email.setContact(new Contact(getContactId()));
		return email;
	}
}
