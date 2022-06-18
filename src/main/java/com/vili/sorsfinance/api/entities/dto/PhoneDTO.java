package com.vili.sorsfinance.api.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.entities.Phone;
import com.vili.sorsfinance.api.entities.enums.PhoneType;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidContactId;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;

public class PhoneDTO extends DTO<Phone> {

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

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
}
