package com.vili.sorsfinance.api.entities;

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
import com.vili.sorsfinance.api.entities.dto.ContactDTO;
import com.vili.sorsfinance.api.entities.enums.ContactType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "preferredContact", "owner", "emails", "phones", "addresses" })
public class Contact extends BusEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer preferredContact;
	@ManyToMany(mappedBy = "contacts")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "contacts" })
	private Set<Address> addresses = new HashSet<>();
	@OneToMany
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "contact" })
	@JoinColumn(name = "contact_id")
	private Set<Email> emails = new HashSet<>();
	@OneToMany
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "contact" })
	@JoinColumn(name = "contact_id")
	private Set<Phone> phones = new HashSet<>();
	@OneToOne
	@JsonIgnoreProperties({ "contact" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JoinColumn(name = "owner_id")
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

	public Contact setPreferredContact(ContactType preferredContact) {
		this.preferredContact = preferredContact.getCode();
		return this;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}
	
	public Contact addAddress(Address address) {
		this.addresses.add(address);
		return this;
	}

	public Contact addAddresses(Address... addresses) {
		for (Address x : addresses) {
			this.addresses.add(x);
			return this;
		}
		return this;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public Contact addEmail(Email email) {
		this.emails.add(email);
		return this;
	}

	public Contact addEmails(Email... emails) {
		for (Email x : emails) {
			this.emails.add(x);
		}
		return this;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public Contact addPhone(Phone phone) {
		this.phones.add(phone);
		return this;
	}

	public Contact addPhones(Phone... phones) {
		for (Phone x : phones) {
			this.phones.add(x);
		}
		return this;
	}

	public Person getOwner() {
		return owner;
	}

	public Contact setOwner(Person owner) {
		this.owner = owner;
		return this;
	}
	
	public static Contact fromDTO(ContactDTO dto) {
		Person owner = new Person(dto.getOwnerId());
		Contact contact = new Contact(dto.getId(), owner, ContactType.toEnum(dto.getPreferredContact()));
		return contact;
	}
}
