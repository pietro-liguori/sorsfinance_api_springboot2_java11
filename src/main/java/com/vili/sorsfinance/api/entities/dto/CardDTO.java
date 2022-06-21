package com.vili.sorsfinance.api.entities.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.entities.enums.CardStatus;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidCard;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;

@ValidCard
public class CardDTO extends DTO<Card> {

	@NotNull(message = "Must not be null")
	private Long accountId;
	@NotBlank(message = "Must not be null or empty")
	@Length(min = 5, max = 60, message = "Must be between 5 and 60 characters")
	private String name;
	@CreditCardNumber(message = "Invalid card number")
	private String number;
	@Future(message = "Cannot insert a expirated card")
	private Date expiration;
	@ValidEnumValue(target = CardType.class)
	private Integer type;
	@ValidEnumValue(target = CardStatus.class)
	private Integer status;
	private Integer closingDay;
	private Double interest;
	private Integer interestUnit;
	private Integer gracePeriod;
	private Integer gracePeriodUnit;

	public CardDTO() {
		super();
	}

	public Long getAccountId() {
		return accountId;
	}

	public CardDTO setAccountId(Long accountId) {
		this.accountId = accountId;
		return this;
	}

	public String getName() {
		return name;
	}

	public CardDTO setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public CardDTO setType(Integer type) {
		this.type = type;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public CardDTO setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public CardDTO setNumber(String number) {
		this.number = number;
		return this;
	}

	public Double getInterest() {
		return interest;
	}

	public CardDTO setInterest(Double interest) {
		this.interest = interest;
		return this;
	}

	public Integer getInterestUnit() {
		return interestUnit;
	}

	public CardDTO setInterestUnit(Integer interestUnit) {
		this.interestUnit = interestUnit;
		return this;
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public CardDTO setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
		return this;
	}

	public Integer getGracePeriodUnit() {
		return gracePeriodUnit;
	}

	public CardDTO setGracePeriodUnit(Integer gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit;
		return this;
	}

	public Integer getClosingDay() {
		return closingDay;
	}

	public CardDTO setClosingDay(Integer closingDay) {
		this.closingDay = closingDay;
		return this;
	}

	public Date getExpiration() {
		return expiration;
	}

	public CardDTO setExpiration(Date expiration) {
		this.expiration = expiration;
		return this;
	}
}
