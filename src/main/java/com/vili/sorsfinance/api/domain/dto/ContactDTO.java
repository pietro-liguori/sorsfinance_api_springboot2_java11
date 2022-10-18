package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.enums.ContactType;
import com.vili.sorsfinance.api.validation.constraints.ValidContact;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidContact
public class ContactDTO extends DataTransferObject {

	private Long ownerId;
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
	
	@Override
	public Contact toEntity() {
		Person owner = new NaturalPerson(getOwnerId());
		Contact contact = new Contact(getId(), owner, ContactType.toEnum(getPreferredContact()));
		return contact;
	}
}
