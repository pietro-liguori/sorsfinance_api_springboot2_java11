package com.vili.sorsfinance.api.entities.dto;

import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidContactId;
import com.vili.sorsfinance.validation.constraints.ValidEmail;

public class EmailDTO extends DTO<com.vili.sorsfinance.api.entities.Email> {

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
}
