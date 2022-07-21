package com.vili.sorsfinance.api.domain.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.domain.Address;
import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.validation.constraints.ValidCityId;
import com.vili.sorsfinance.api.validation.constraints.ValidContactId;
import com.vili.sorsfinance.framework.DataTransferObject;

public class AddressDTO extends DataTransferObject {

	@NotBlank(message = "Must not be null or empty")
	private String address;
	@NotBlank(message = "Must not be null or empty")
	private String number;
	private String complement;
	@NotBlank(message = "Must not be null or empty")
	private String district;
	@NotBlank(message = "Must not be null or empty")
	private String zipCode;
	@NotNull(message = "Must not be null")
	private Boolean preferred;
	@ValidCityId
	private Long cityId;
	@NotEmpty(message = "Must not be null or empty")
	private Set<@ValidContactId(value = "contactIds[]") Long> contactIds = new HashSet<>();

	public AddressDTO() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public AddressDTO setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public AddressDTO setNumber(String number) {
		this.number = number;
		return this;
	}

	public String getComplement() {
		return complement;
	}

	public AddressDTO setComplement(String complement) {
		this.complement = complement;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public AddressDTO setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getZipCode() {
		return zipCode;
	}

	public AddressDTO setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public AddressDTO setPreferred(Boolean preferred) {
		this.preferred = preferred;
		return this;
	}

	public Long getCityId() {
		return cityId;
	}

	public AddressDTO setCityId(Long cityId) {
		this.cityId = cityId;
		return this;
	}

	public Set<Long> getContactIds() {
		return contactIds;
	}

	public AddressDTO setContactIds(Set<Long> contactIds) {
		this.contactIds = contactIds;
		return this;
	}
	
	@Override
	public Address toEntity() {
		City city = new City(getCityId());
		Address address = new Address(getId(), getAddress(), getNumber(), getComplement(), getDistrict(),
				getZipCode(), city, getPreferred());
		
		for (Long contactId : getContactIds()) {
			Contact contact = new Contact(contactId);
			address.addContact(contact);
		}
		
		return address;
	}
}
