package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.vili.sorsfinance.entities.enums.PersonProfile;
import com.vili.sorsfinance.entities.enums.PersonType;

@Entity
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String socialId;
	private Integer type;
	private Integer profile;
	@OneToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;

	public Person() {
		
	}

	public Person(Long id, String name, String socialId, PersonType type, PersonProfile profile) {
		super();
		this.id = id;
		this.name = name;
		this.socialId = socialId;
		this.type = type.getCode();
		this.profile = profile.getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public PersonType getType() {
		return PersonType.toEnum(type);
	}

	public void setType(PersonType type) {
		this.type = type.getCode();
	}

	public PersonProfile getProfile() {
		return PersonProfile.toEnum(profile);
	}

	public void setProfile(PersonProfile profile) {
		this.profile = profile.getCode();
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
}
