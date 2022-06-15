package com.vili.sorsfinance.api.entities.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.enums.PeriodUnit;
import com.vili.sorsfinance.api.framework.DTO;

public class AccountDTO extends DTO<Account> {

	private Long id;
	@NotBlank(message = "Must not be null or empty")
	@Positive(message = "Must be a positive long")
	private Long holderId;
	@NotBlank(message = "Must not be null or empty")
	@Length(min = 5, max = 60, message = "Must be between 5 and 60 characters")
	private String name;
	@NotBlank(message = "Must not be null or empty")
	private Double balance;
	@NotBlank(message = "Must not be null or empty")
	@Min(value = 1, message = "Enum value must be between 1 and 5")
	@Max(value = 5, message = "Enum value must be between 1 and 5")
	private Integer type;
	@NotBlank(message = "Must not be null or empty")
	@Min(value = 1, message = "Enum value must be between 1 and 3")
	@Max(value = 3, message = "Enum value must be between 1 and 3")
	private Integer status;
	private Long bankId;
	private List<Long> cards;
	// bank account
	@NotBlank(message = "Must not be null or empty")
	private String number;
	@NotBlank(message = "Must not be null or empty")
	private String agency;
	@NotBlank(message = "Must not be null or empty")
	@Positive(message = "Must be a positive double")
	private Double overdraft;
	@NotBlank(message = "Must not be null or empty")
	@PositiveOrZero(message = "Must be a positive double or zero")
	private Double interest;
	private Integer interestUnit;
	@NotBlank(message = "Must not be null or empty")
	@PositiveOrZero(message = "Must be a positive integer or zero")
	private Integer gracePeriod;
	private Integer gracePeriodUnit;
	@NotBlank(message = "Must not be null or empty")
	@PositiveOrZero(message = "Must be a positive double or zero")
	private Double creditLimit;
	// wallet
	@NotBlank(message = "Must not be null or empty")
	@PositiveOrZero(message = "Must be a positive double or zero")
	private Double savings;

	public AccountDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getType() {
		return AccountType.toEnum(type).getLabel();
	}

	public void setType(AccountType type) {
		this.type = type.getCode();
	}

	public String getStatus() {
		return AccountStatus.toEnum(status).getLabel();
	}

	public void setStatus(AccountStatus status) {
		this.status = status.getCode();
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public List<Long> getCards() {
		return cards;
	}

	public void setCards(List<Long> cards) {
		this.cards = cards;
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

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}
}
