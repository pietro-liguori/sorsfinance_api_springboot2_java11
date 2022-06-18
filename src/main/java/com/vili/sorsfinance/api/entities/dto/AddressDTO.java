package com.vili.sorsfinance.api.entities.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.entities.Address;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidCityId;
import com.vili.sorsfinance.validation.constraints.ValidContactList;

public class AddressDTO extends DTO<Address> {

	@NotBlank(message = "Must not be null or empty")
	private String address;
	@NotBlank(message = "Must not be empty")
	private String number;
	private String complement;
	@NotBlank(message = "Must not be empty")
	private String district;
	@NotBlank(message = "Must not be empty")
	private String zipCode;
	@NotNull(message = "Must not be null")
	private Boolean preferred;
	@ValidCityId
	private Long cityId;
	@ValidContactList
	private Set<Long> contactIds = new HashSet<>();

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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Set<Long> getContactIds() {
		return contactIds;
	}

	public void setContactIds(Set<Long> contactIds) {
		this.contactIds = contactIds;
	}
}
