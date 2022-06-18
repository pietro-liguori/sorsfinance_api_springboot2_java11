package com.vili.sorsfinance.api.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@ManyToMany
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JoinTable(name = "contact_address", joinColumns = @JoinColumn(name = "contact_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	@JsonIgnoreProperties({ "contact" })
	private Set<Address> addresses = new HashSet<>();
	@OneToMany
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JoinColumn(name = "contact_id")
	@JsonIgnoreProperties({ "contact" })
	private Set<Email> emails = new HashSet<>();
	@OneToMany
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JoinColumn(name = "contact_id")
	@JsonIgnoreProperties({ "contact" })
	private Set<Phone> phones = new HashSet<>();
	@OneToOne(mappedBy = "contact")
	@JsonIgnoreProperties({ "contact" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Person owner;

	public Contact() {
		super();
	}
	
	public Contact(Long id) {
		super(id, Contact.class);
	}

	public Contact(Long id, ContactType preferredContact) {
		super(id, Contact.class);
		this.preferredContact = preferredContact.getCode();
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

	public Set<Phone> getPhones() {
		return phones;
	}

	public void addEmails(Email... emails) {
		for (Email x : emails) {
			this.emails.add(x);
		}
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

	public Contact setOwner(Person owner) {
		this.owner = owner;
		return this;
	}
	
	public static Contact fromDTO(ContactDTO dto) {
		Contact contact = new Contact(dto.getId(), ContactType.toEnum(dto.getPreferredContact()));
		contact.setOwner(new Person(dto.getOwnerId()));
		return contact;
	}
}
