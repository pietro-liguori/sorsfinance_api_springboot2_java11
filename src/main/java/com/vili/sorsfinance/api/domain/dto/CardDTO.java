package com.vili.sorsfinance.api.domain.dto;

import java.util.Date;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankCard;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.CreditCard;
import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.api.domain.enums.CardStatus;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.validation.constraints.ValidCard;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidCard
public class CardDTO extends DataTransferObject {

	private Long accountId;
	private String name;
	private String number;
	private Integer type;
	private Integer status;
	private Date expiration;
	private Integer closingDay;
	private Double interest;
	private Integer interestUnit;
	private Integer gracePeriod;
	private Integer gracePeriodUnit;
	private String printedName;
	private Double balance;
	private Double limit;

	public CardDTO() {
		super();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public Integer getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(Integer closingDay) {
		this.closingDay = closingDay;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public String getPrintedName() {
		return printedName;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	@Override
	public Card toEntity() {
		Account acc = new Account(getAccountId());
		CardType type = CardType.toEnum(getType());
		
		if (Card.CREDIT_CARD_TYPES.contains(type)) {
			return new CreditCard(getId(), getName(), acc, getPrintedName(), getNumber(), getExpiration(), getClosingDay(), getGracePeriod(), PeriodUnit.toEnum(getGracePeriodUnit()), getInterest(), PeriodUnit.toEnum(getInterestUnit()), getLimit(), CardType.toEnum(getType()), CardStatus.toEnum(getStatus()));
		}
		
		if (Card.BANK_CARD_TYPES.contains(type)) {
			return new BankCard(getId(), getName(), acc, getPrintedName(), getNumber(), getExpiration(), CardType.toEnum(getType()), CardStatus.toEnum(getStatus()));
		}

		if (Card.VOUCHER_TYPES.contains(type)) {
			return new Voucher(getId(), getName(), acc, getNumber(), getExpiration(), getBalance(), CardType.toEnum(getType()), CardStatus.toEnum(getStatus()));
		}
		
		return null;
	}
}
