package com.vili.sorsfinance.api.entities.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidContactId;

public class EmailDTO extends DTO<com.vili.sorsfinance.api.entities.Email> {

	@Email(message = "Invalid email")
	@NotBlank(message = "Must not be null or empty")
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

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
}
