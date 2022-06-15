package com.vili.sorsfinance.api.entities;

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
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.entities.enums.PersonType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "name", "socialId", "profile", "type", "branch", "contact" })
public class Person extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String socialId;
	private Integer type;
	private Integer profile;
	@OneToOne
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "owner" })
	@JoinColumn(name = "contact_id")
	private Contact contact;
	@ManyToOne
	@JoinColumn(name = "branch_id")
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

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getType() {
		return PersonType.toEnum(type).getLabel();
	}

	public void setType(PersonType type) {
		this.type = type.getCode();
	}

	public String getProfile() {
		return PersonProfile.toEnum(profile).getLabel();
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

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public void addTransactions(Transaction... transactions) {
		for (Transaction x : transactions) {
			this.transactions.add(x);
		}
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void addAccounts(Account... accounts) {
		for (Account x : accounts) {
			this.accounts.add(x);
		}
	}
}
