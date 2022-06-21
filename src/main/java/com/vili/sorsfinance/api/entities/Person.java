package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.PersonDTO;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.entities.enums.PersonType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "name", "socialId", "profile", "type", "branch", "contact" })
public class Person extends BusEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String socialId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer profile;
	@OneToOne(mappedBy = "owner")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "owner" })
	private Contact contact;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Branch branch;
	@JsonIgnore
	@OneToMany(mappedBy = "holder")
	private Set<Account> accounts = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "recipient")
	private Set<Transaction> transactions = new HashSet<>();

	public Person() {
		super();
	}
	
	public Person(Long id) {
		super(id, Person.class);
	}

	public Person(Long id, String name, String socialId, PersonType type, PersonProfile profile) {
		super(id, Person.class);
		this.name = name;
		this.socialId = socialId;
		this.type = type == null ? null : type.getCode();
		this.profile = profile == null ? null : profile.getCode();
	}

	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public String getSocialId() {
		return socialId;
	}

	public Person setSocialId(String socialId) {
		this.socialId = socialId;
		return this;
	}

	public String getType() {
		if (type == null)
			return null;
		
		return PersonType.toEnum(type).getLabel();
	}

	public Person setType(PersonType type) {
		this.type = type.getCode();
		return this;
	}

	public String getProfile() {
		if (profile == null)
			return null;
		
		return PersonProfile.toEnum(profile).getLabel();
	}

	public Person setProfile(PersonProfile profile) {
		this.profile = profile.getCode();
		return this;
	}

	public Contact getContact() {
		return contact;
	}

	public Person setContact(Contact contact) {
		this.contact = contact;
		return this;
	}

	public Branch getBranch() {
		return branch;
	}

	public Person setBranch(Branch branch) {
		this.branch = branch;
		return this;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public Person addTransaction(Transaction transaction) {
		transactions.add(transaction);
		return this;
	}

	public Person addTransactions(Transaction... transactions) {
		for (Transaction x : transactions) {
			this.transactions.add(x);
		}
		return this;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public Person addAccount(Account account) {
		accounts.add(account);
		return this;
	}
	
	public Person addAccounts(Account... accounts) {
		for (Account x : accounts) {
			this.accounts.add(x);
		}
		return this;
	}
	
	public static Person fromDTO(PersonDTO dto) {
		Person person = new Person(dto.getId(), dto.getName(), dto.getSocialId(), PersonType.toEnum(dto.getType()), PersonProfile.toEnum(dto.getProfile()));
		person.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return person;
	}
}
