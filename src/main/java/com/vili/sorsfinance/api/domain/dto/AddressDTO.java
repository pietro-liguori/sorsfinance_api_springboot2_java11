package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Address;
import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.enums.AddressType;
import com.vili.sorsfinance.api.validation.constraints.ValidAddress;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidAddress
public class AddressDTO extends DataTransferObject {

	private String address;
	private String number;
	private String complement;
	private String district;
	private String zipCode;
	private Boolean preferred;
	private Integer type;
	private Long cityId;
	private Long contactId;

	public AddressDTO() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	
	@Override
	public Address toEntity() {
		City city = new City(getCityId());
		Address address = new Address(getId(), getAddress(), getNumber(), getComplement(), getDistrict(),
				getZipCode(), city, getPreferred(), AddressType.toEnum(getType()));
		Contact contact = new Contact(contactId);
		address.setContact(contact);
		
		return address;
	}
}
