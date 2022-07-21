package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.ContactType;
import com.vili.sorsfinance.api.repositories.ContactRepository;
import com.vili.sorsfinance.api.services.ContactService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(value = ContactService.class)
@RepositoryRef(value = ContactRepository.class)
@JsonPropertyOrder({ "id", "preferredContact", "owner", "emails", "phones", "addresses" })
public class Contact extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer preferredContact;
	
	@ManyToMany(mappedBy = "contacts")
	@FilterSetting(alias = "address", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "contacts" })
	private Set<Address> addresses = new HashSet<>();
	
	@OneToMany
	@JoinColumn(name = "contact_id")
	@FilterSetting(alias = "email", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "contact" })
	private Set<Email> emails = new HashSet<>();
	
	@OneToMany
	@JoinColumn(name = "contact_id")
	@FilterSetting(alias = "phone", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "contact" })
	private Set<Phone> phones = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "owner_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "contact" })
	private Person owner;

	public Contact() {
		super();
	}
	
	public Contact(Long id) {
		super(id, Contact.class);
	}

	public Contact(Long id, Person owner, ContactType preferredContact) {
		super(id, Contact.class);
		this.preferredContact = preferredContact.getCode();
		this.owner = owner;
	}

	public String getPreferredContact() {
		return ContactType.toEnum(preferredContact).getLabel();
	}

	public void setPreferredContact(ContactType preferredContact) {
		this.preferredContact = preferredContact.getCode();
	}

	public Set<Address> getAddresses() {
		return addresses;
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public void addAddresses(Address... addresses) {
		for (Address x : addresses) {
			this.addresses.add(x);
		}
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void addEmail(Email email) {
		this.emails.add(email);
	}

	public void addEmails(Email... emails) {
		for (Email x : emails) {
			this.emails.add(x);
		}
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void addPhone(Phone phone) {
		this.phones.add(phone);
	}

	public void addPhones(Phone... phones) {
		for (Phone x : phones) {
			this.phones.add(x);
		}
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
}
