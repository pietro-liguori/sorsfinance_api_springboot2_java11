package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.AddressDTO;
import com.vili.sorsfinance.api.framework.BusEntity;
import com.vili.sorsfinance.api.framework.DTOType;

@Entity
@JsonPropertyOrder({ "id", "address", "number", "complement", "district", "zipCode", "city", "contact", "preferred" })
public class Address extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String address;
	private String number;
	private String complement;
	private String district;
	private String zipCode;
	private Boolean preferred;
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;
	@ManyToMany(mappedBy = "addresses")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "addresses", "phones", "emails", "preferredContact" })
	private Set<Contact> contacts = new HashSet<>();

	public Address() {
		super();
	}

	public Address(Long id) {
		super(id, Address.class);
	}
	
	public Address(Long id, String address, String number, String complement, String district, String zipCode,
			City city, Boolean preferred) {
		super(id, Address.class);
		this.address = address;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.zipCode = zipCode;
		this.city = city;
		this.preferred = preferred;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}

	public void addContacts(Contact... contacts) {
		for (Contact x : contacts) {
			this.contacts.add(x);
		}
	}

	public static Address fromDTO(AddressDTO dto) {
		City city = new City(dto.getCityId());
		Address address = new Address(dto.getId(), dto.getAddress(), dto.getNumber(), dto.getComplement(), dto.getDistrict(),
				dto.getZipCode(), city, dto.getPreferred());
		
		for (Long contactId : dto.getContactIds()) {
			address.getContacts().add(new Contact(contactId));
		}
		
		if (dto.getMethod().equals(DTOType.INSERT))
			address.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		
		address.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return address;
	}
}
