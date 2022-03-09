package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.ContactType;

@Entity
public class Contact implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private ContactType preferredContact;
	@OneToMany
	@JoinColumn(name = "contact_id")
	private Set<Address> addresses = new HashSet<>();
	@OneToMany
	@JoinColumn(name = "contact_id")
	private Set<Email> emails = new HashSet<>();
	@OneToMany
	@JoinColumn(name = "contact_id")
	private Set<Phone> phones = new HashSet<>();
	@JsonIgnore
	@OneToOne(mappedBy = "contact")
	private Person owner;

	public Contact() {
		
	}

	public Contact(Long id, ContactType preferredContact) {
		super();
		this.id = id;
		this.preferredContact = preferredContact;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContactType getPreferredContact() {
		return preferredContact;
	}

	public void setPreferredContact(ContactType preferredContact) {
		this.preferredContact = preferredContact;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void addEmail(Email email) {
		this.emails.add(email);
	}

	public void addPhone(Phone phone) {
		this.phones.add(phone);
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	@JsonIgnore
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(id, other.id);
	}
}
