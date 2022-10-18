package com.vili.sorsfinance.api.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.repositories.BankAccountRepository;
import com.vili.sorsfinance.api.services.BankAccountService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(BankAccountService.class)
@RepositoryRef(BankAccountRepository.class)
public class BankAccount extends VoucherAccount {

	private static final long serialVersionUID = 1L;
	
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
	private Double balance;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double savings;

	@OneToMany(mappedBy = "account")
	@NoFilter
	private Set<Card> cards = new HashSet<>();

	public BankAccount() {
		super();
	}

	public BankAccount(Long id) {
		super(id, BankAccount.class);
	}

	public BankAccount(Long id, String name, Person holder, String number, String agency, Person bank, Double balance,
			Double overdraft, Double interest, PeriodUnit interestUnit, Integer gracePeriod, PeriodUnit gracePeriodUnit,
			Double savings, AccountType type, AccountStatus status) {
		super(id, name, number, holder, bank, type, status, BankAccount.class);
		this.balance = balance;
		this.agency = agency;
		this.overdraft = overdraft;
		this.interest = interest;
		this.interestUnit = interestUnit == null ? null : interestUnit.getCode();
		this.gracePeriod = gracePeriod;
		this.gracePeriodUnit = gracePeriodUnit == null ? null : gracePeriodUnit.getCode();
		this.savings = savings;
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

	public Integer getInterestUnit() {
		return interestUnit;
	}

	public void setInterestUnit(PeriodUnit interestUnit) {
		this.interestUnit = interestUnit == null ? null : interestUnit.getCode();
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public Integer getGracePeriodUnit() {
		return gracePeriodUnit;
	}

	public void setGracePeriodUnit(PeriodUnit gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit == null ? null : gracePeriodUnit.getCode();
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@JsonGetter
	@JsonIgnoreProperties({ "account" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public List<BankCard> getCards() {
		List<BankCard> ret  = new ArrayList<>();
		
		for (Card card : cards) {
			if (BankCard.class.isAssignableFrom(card.getClass()))
				ret.add((BankCard) card);
		}
		
		return ret;
	}

	public void addCard(BankCard card) {
		cards.add(card);
	}

	public void addCards(BankCard... cards) {
		for (BankCard x : cards) {
			this.cards.add(x);
		}
	}
}
