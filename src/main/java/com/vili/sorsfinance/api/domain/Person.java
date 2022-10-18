package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.services.PersonService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(value = PersonService.class)
@RepositoryRef(value = PersonRepository.class)
@JsonPropertyOrder({ "id", "name", "nickname", "tradeName", "socialId", "birthDate", "foundationDate", "status", "profile", "type", "branch", "contact" })
public class Person extends BusinessEntity {

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
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "owner" })
	private Contact contact;
	
	@OneToMany(mappedBy = "holder")
	@NoFilter
	@JsonIgnore
	private Set<Account> accounts = new HashSet<>();
	
	@OneToMany(mappedBy = "recipient")
	@NoFilter
	@JsonIgnore
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

	protected Person(Long id, Class<?> domain) {
		super(id, domain);
	}

	protected Person(Long id, String name, String socialId, PersonType type, PersonProfile profile, Class<?> domain) {
		super(id, domain);
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

	public Integer getType() {
		return type;
	}

	public void setType(PersonType type) {
		this.type = type == null ? null : type.getCode();
	}

	public Integer getProfile() {
		return profile;
	}

	public void setProfile(PersonProfile profile) {
		this.profile = profile == null ? null : profile.getCode();
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Transaction> getTransactions() {
		return transactions.stream().toList();
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public void addTransactions(Transaction... transactions) {
		for (Transaction x : transactions) {
			this.transactions.add(x);
		}
	}

	public List<Account> getAccounts() {
		return accounts.stream().toList();
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
