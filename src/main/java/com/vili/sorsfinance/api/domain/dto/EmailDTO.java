package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.api.validation.constraints.ValidEmail;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidEmail
public class EmailDTO extends DataTransferObject {

	private String name;
	private Boolean preferred;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	
	@Override
	public Email toEntity() {
		Email email = new Email(getId(), getName(), getPreferred());
		email.setContact(new Contact(getContactId()));
		return email;
	}
}
