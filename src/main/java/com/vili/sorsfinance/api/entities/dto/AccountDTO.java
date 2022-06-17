package com.vili.sorsfinance.api.entities.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidAccount;
import com.vili.sorsfinance.validation.constraints.ValidPersonId;

@ValidAccount
public class AccountDTO extends DTO<Account> {

	@ValidPersonId(profile = PersonProfile.HOLDER)
	private Long holderId;
	@NotEmpty(message = "Must not be empty")
	@Length(min = 5, max = 60, message = "Must be between 5 and 60 characters")
	private String name;
	@NotNull(message = "Must not be null")
	private Double balance;
	@NotNull(message = "Must not be null")
	private Integer type;
	@NotNull(message = "Must not be null")
	private Integer status;
	private Long bankId;
	private List<Long> cardIds;
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

	public void setHolderId(Long holderId) {
		this.holderId = holderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public List<Long> getCardIds() {
		return cardIds;
	}

	public void setCardIds(List<Long> cards) {
		this.cardIds = cards;
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

	public Integer getInterestUnit() {
		return interestUnit;
	}

	public void setInterestUnit(Integer interestUnit) {
		this.interestUnit = interestUnit;
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

	public void setGracePeriodUnit(Integer gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}
}
