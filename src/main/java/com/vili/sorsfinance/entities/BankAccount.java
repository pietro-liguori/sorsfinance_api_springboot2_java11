package com.vili.sorsfinance.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.vili.sorsfinance.entities.enums.AccountStatus;
import com.vili.sorsfinance.entities.enums.AccountType;
import com.vili.sorsfinance.entities.enums.PeriodUnit;

@Entity
public class BankAccount extends Account {

	private static final long serialVersionUID = 1L;

	private String number;
	private String agency;
	private Double overdraft;
	private Double interest;
	private Integer interestUnit;
	private Integer gracePeriod;
	private Integer gracePeriodUnit;
	private Double creditLimit;
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Person bank;
	@OneToMany(mappedBy = "account")
	private Set<Card> cards = new HashSet<>();

	public BankAccount() {
	}

	public BankAccount(Long id, String name, Person holder, String number, String agency, Person bank, Double balance,
			Double overdraft, Double interest, PeriodUnit interestUnit, Integer gracePeriod, PeriodUnit gracePeriodUnit,
			Double creditLimit, AccountType type, AccountStatus status) {
		super(id, name, holder, balance, type, status);
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

	public PeriodUnit getInterestUnit() {
		return PeriodUnit.toEnum(interestUnit);
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

	public PeriodUnit getGracePeriodUnit() {
		return PeriodUnit.toEnum(gracePeriodUnit);
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
}