package com.vili.sorsfinance.api.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.AddressType;
import com.vili.sorsfinance.api.repositories.AddressRepository;
import com.vili.sorsfinance.api.services.AddressService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(AddressService.class)
@RepositoryRef(AddressRepository.class)
@JsonPropertyOrder({ "id", "type", "address", "number", "complement", "district", "zipCode", "city", "preferred", "contact" })
public class Address extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String address;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String complement;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String district;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String zipCode;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean preferred;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;

	@OneToOne
	@JoinColumn(name = "city_id")
	@FilterSetting(nesting = { "id" })
	@FilterSetting(alias = "state", nesting = { "state", "id" })
	@FilterSetting(alias = "country", nesting = { "state", "country", "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private City city;
	
	@ManyToOne
	@FilterSetting(alias = "owner", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "addresses", "phones", "emails" })
	private Contact contact;

	public Address() {
		super();
	}

	public Address(Long id) {
		super(id, Address.class);
	}
	
	public Address(Long id, String address, String number, String complement, String district, String zipCode,
			City city, Boolean preferred, AddressType type) {
		super(id, Address.class);
		this.address = address;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.zipCode = zipCode;
		this.city = city;
		this.preferred = preferred;
		this.type = type == null ? null : type.getCode();
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

	public void setType(AddressType type) {
		this.type = type == null ? null : type.getCode();
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
