package com.vili.sorsfinance.api.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.Phone;
import com.vili.sorsfinance.api.domain.enums.PhoneType;
import com.vili.sorsfinance.api.validation.constraints.ValidContactId;
import com.vili.sorsfinance.api.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.framework.DataTransferObject;

public class PhoneDTO extends DataTransferObject {

	@NotBlank(message = "Must not be null or empty")
	private String number;
	@ValidEnumValue(target = PhoneType.class)
	private Integer type;
	@NotNull(message = "Must not be null")
	private Boolean preferred;
	@ValidContactId
	private Long contactId;

	public PhoneDTO() {
		super();
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public PhoneDTO setPreferred(Boolean preferred) {
		this.preferred = preferred;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public PhoneDTO setNumber(String number) {
		this.number = number;
		return this;
	}

	public Long getContactId() {
		return contactId;
	}

	public PhoneDTO setContactId(Long contactId) {
		this.contactId = contactId;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public PhoneDTO setType(Integer type) {
		this.type = type;
		return this;
	}
	
	@Override
	public Phone toEntity() {
		Phone phone = new Phone(getId(), getNumber(),PhoneType.toEnum(getType()), getPreferred());
		phone.setContact(new Contact(getContactId()));
		return phone;
	}
}
