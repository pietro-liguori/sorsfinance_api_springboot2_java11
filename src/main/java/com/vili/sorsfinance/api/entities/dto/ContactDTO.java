package com.vili.sorsfinance.api.entities.dto;

import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.entities.enums.ContactType;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidContact;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;

@ValidContact
public class ContactDTO extends DTO<Contact> {

	private Long ownerId;
	@ValidEnumValue(target = ContactType.class)
	private Integer preferredContact;

	public ContactDTO() {
		super();
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getPreferredContact() {
		return preferredContact;
	}

	public void setPreferredContact(Integer preferredContact) {
		this.preferredContact = preferredContact;
	}
}
