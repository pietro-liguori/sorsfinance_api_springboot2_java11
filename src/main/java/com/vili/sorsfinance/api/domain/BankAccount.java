package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.repositories.BankAccountRepository;
import com.vili.sorsfinance.api.services.BankAccountService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(BankAccountService.class)
@RepositoryRef(BankAccountRepository.class)
public class BankAccount extends Account {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String agency;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double overdraft;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double interest;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer interestUnit;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer gracePeriod;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer gracePeriodUnit;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double creditLimit;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Person bank;
	
	@OneToMany(mappedBy = "account")
	@FilterSetting(alias = "card", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "account" })
	private Set<Card> cards = new HashSet<>();

	public BankAccount() {
		super();
	}

	public BankAccount(Long id) {
		super(id, BankAccount.class);
	}

	public BankAccount(Long id, String name, Person holder, String number, String agency, Person bank, Double balance,
			Double overdraft, Double interest, PeriodUnit interestUnit, Integer gracePeriod, PeriodUnit gracePeriodUnit,
			Double creditLimit, AccountType type, AccountStatus status) {
		super(id, name, holder, balance, type, status, BankAccount.class);
		this.number = number;
		this.agency = agency;
		this.bank = bank;
		this.overdraft = overdraft;
		this.interest = interest;
		this.interestUnit = interestUnit.getCode();
		this.gracePeriod = gracePeriod;
		this.gracePeriodUnit = gracePeriodUnit.getCode();
		this.creditLimit = creditLimit;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Double overdraft) {
		this.overdraft = overdraft;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public String getInterestUnit() {
		return PeriodUnit.toEnum(interestUnit).getLabel();
	}

	public void setInterestUnit(PeriodUnit interestUnit) {
		this.interestUnit = interestUnit.getCode();
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public String getGracePeriodUnit() {
		return PeriodUnit.toEnum(gracePeriodUnit).getLabel();
	}

	public void setGracePeriodUnit(PeriodUnit gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit.getCode();
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Person getBank() {
		return bank;
	}

	public void setBank(Person bank) {
		this.bank = bank;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void addCards(Card... cards) {
		for (Card x : cards) {
			this.cards.add(x);
		}
	}
}
