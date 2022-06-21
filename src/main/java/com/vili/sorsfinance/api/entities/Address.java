package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@ManyToMany
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "addresses", "phones", "emails", "preferredContact" })
	@JoinTable(name = "contact_address", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
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

	public Address setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public Address setNumber(String number) {
		this.number = number;
		return this;
	}

	public String getComplement() {
		return complement;
	}

	public Address setComplement(String complement) {
		this.complement = complement;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public Address setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getZipCode() {
		return zipCode;
	}

	public Address setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public Address setPreferred(Boolean preferred) {
		this.preferred = preferred;
		return this;
	}

	public City getCity() {
		return city;
	}

	public Address setCity(City city) {
		this.city = city;
		return this;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public Address addContact(Contact contact) {
		this.contacts.add(contact);
		return this;
	}

	public Address addContacts(Contact... contacts) {
		for (Contact x : contacts) {
			this.contacts.add(x);
		}
		return this;
	}

	public static Address fromDTO(AddressDTO dto) {
		City city = new City(dto.getCityId());
		Address address = new Address(dto.getId(), dto.getAddress(), dto.getNumber(), dto.getComplement(), dto.getDistrict(),
				dto.getZipCode(), city, dto.getPreferred());
		
		for (Long contactId : dto.getContactIds()) {
			Contact contact = new Contact(contactId);
			address.addContact(contact);
		}
		
		if (dto.getMethod().equals(DTOType.INSERT))
			address.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		
		address.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return address;
	}
}
