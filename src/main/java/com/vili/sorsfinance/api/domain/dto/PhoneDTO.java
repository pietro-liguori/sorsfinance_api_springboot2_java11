package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.Phone;
import com.vili.sorsfinance.api.domain.enums.PhoneType;
import com.vili.sorsfinance.api.validation.constraints.ValidPhone;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidPhone
public class PhoneDTO extends DataTransferObject {

	private String number;
	private Integer type;
	private Boolean preferred;
	private Long countryId;
	private Long contactId;

	public PhoneDTO() {
		super();
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Override
	public Phone toEntity() {
		Phone phone = new Phone(getId(), getNumber(), new Country(getCountryId()),PhoneType.toEnum(getType()), getPreferred());
		phone.setContact(new Contact(getContactId()));
		return phone;
	}
}
