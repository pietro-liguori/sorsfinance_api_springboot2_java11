package com.vili.sorsfinance.api.entities.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidAccount;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.validation.constraints.ValidPersonId;

@ValidAccount
public class AccountDTO extends DTO<Account> {

	@ValidPersonId(profile = PersonProfile.HOLDER)
	private Long holderId;
	@NotBlank(message = "Must not be null or empty")
	@Length(min = 5, max = 60, message = "Must be between 5 and 60 characters")
	private String name;
	@NotNull(message = "Must not be null")
	private Double balance;
	@ValidEnumValue(target = AccountType.class)
	private Integer type;
	@ValidEnumValue(target = AccountStatus.class)
	private Integer status;
	private Long bankId;
	private Set<Long> cardIds;
	private String number;
	private String agency;
	private Double overdraft;
	private Double interest;
	private Integer interestUnit;
	private Integer gracePeriod;
	private Integer gracePeriodUnit;
	private Double creditLimit;
	private Double savings;

	public AccountDTO() {
		super();
	}

	public Long getHolderId() {
		return holderId;
	}

	public AccountDTO setHolderId(Long holderId) {
		this.holderId = holderId;
		return this;
	}

	public String getName() {
		return name;
	}

	public AccountDTO setName(String name) {
		this.name = name;
		return this;
	}

	public Double getBalance() {
		return balance;
	}

	public AccountDTO setBalance(Double balance) {
		this.balance = balance;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public AccountDTO setType(Integer type) {
		this.type = type;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public AccountDTO setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Long getBankId() {
		return bankId;
	}

	public AccountDTO setBankId(Long bankId) {
		this.bankId = bankId;
		return this;
	}

	public Set<Long> getCardIds() {
		return cardIds;
	}

	public AccountDTO setCardIds(Set<Long> cards) {
		this.cardIds = cards;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public AccountDTO setNumber(String number) {
		this.number = number;
		return this;
	}

	public String getAgency() {
		return agency;
	}

	public AccountDTO setAgency(String agency) {
		this.agency = agency;
		return this;
	}

	public Double getOverdraft() {
		return overdraft;
	}

	public AccountDTO setOverdraft(Double overdraft) {
		this.overdraft = overdraft;
		return this;
	}

	public Double getInterest() {
		return interest;
	}

	public AccountDTO setInterest(Double interest) {
		this.interest = interest;
		return this;
	}

	public Integer getInterestUnit() {
		return interestUnit;
	}

	public AccountDTO setInterestUnit(Integer interestUnit) {
		this.interestUnit = interestUnit;
		return this;
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public AccountDTO setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
		return this;
	}

	public Integer getGracePeriodUnit() {
		return gracePeriodUnit;
	}

	public AccountDTO setGracePeriodUnit(Integer gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit;
		return this;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public AccountDTO setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
		return this;
	}

	public Double getSavings() {
		return savings;
	}

	public AccountDTO setSavings(Double savings) {
		this.savings = savings;
		return this;
	}
}
