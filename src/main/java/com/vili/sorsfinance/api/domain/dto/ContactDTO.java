package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.enums.ContactType;
import com.vili.sorsfinance.api.validation.constraints.ValidContact;
import com.vili.sorsfinance.api.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidContact
public class ContactDTO extends DataTransferObject {

	private Long ownerId;
	@ValidEnumValue(target = ContactType.class)
	private Integer preferredContact;

	public ContactDTO() {
		super();
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public ContactDTO setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	public Integer getPreferredContact() {
		return preferredContact;
	}

	public ContactDTO setPreferredContact(Integer preferredContact) {
		this.preferredContact = preferredContact;
		return this;
	}
	
	@Override
	public Contact toEntity() {
		Person owner = new Person(getOwnerId());
		Contact contact = new Contact(getId(), owner, ContactType.toEnum(getPreferredContact()));
		return contact;
	}
}
