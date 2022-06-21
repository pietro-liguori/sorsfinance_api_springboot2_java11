package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.vili.sorsfinance.api.entities.dto.CardDTO;
import com.vili.sorsfinance.api.entities.enums.CardStatus;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.entities.enums.PeriodUnit;

@Entity
public class CreditCard extends Card {

	private static final long serialVersionUID = 1L;

	private Integer closingDay;
	private Integer gracePeriod;
	private Integer gracePeriodUnit = PeriodUnit.DAY.getCode();
	private Double interest;
	private Integer interestUnit = PeriodUnit.MONTH.getCode();

	public CreditCard() {
	}

	public CreditCard(Long id, String name, Account account, String number, Date expiration, CardType type,
			CardStatus status, Integer closingDay, Integer gracePeriod, PeriodUnit gracePeriodUnit, Double interest,
			PeriodUnit interestUnit) {
		super(id, name, account, number, expiration, type, status, CreditCard.class);
		this.closingDay = closingDay;
		this.gracePeriod = gracePeriod;
		this.gracePeriodUnit = gracePeriodUnit.getCode();
		this.interest = interest;
		this.interestUnit = interestUnit.getCode();
	}

	public Integer getClosingDay() {
		return closingDay;
	}

	public CreditCard setClosingDay(Integer closingDay) {
		this.closingDay = closingDay;
		return this;
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public CreditCard setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
		return this;
	}

	public String getGracePeriodUnit() {
		return PeriodUnit.toEnum(gracePeriodUnit).getLabel();
	}

	public CreditCard setGracePeriodUnit(PeriodUnit gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit.getCode();
		return this;
	}

	public Double getInterest() {
		return interest;
	}

	public CreditCard setInterest(Double interest) {
		this.interest = interest;
		return this;
	}

	public String getInterestUnit() {
		return PeriodUnit.toEnum(interestUnit).getLabel();
	}

	public CreditCard setInterestUnit(PeriodUnit interestUnit) {
		this.interestUnit = interestUnit.getCode();
		return this;
	}

	public static CreditCard fromDTO(CardDTO dto) {
		Account acc = new Account(dto.getAccountId());
		CreditCard card = new CreditCard(dto.getId(), dto.getName(), acc, dto.getNumber(), dto.getExpiration(),
				CardType.toEnum(dto.getType()), CardStatus.toEnum(dto.getStatus()), dto.getClosingDay(),
				dto.getGracePeriod(), PeriodUnit.toEnum(dto.getGracePeriodUnit()), dto.getInterest(),
				PeriodUnit.toEnum(dto.getInterestUnit()));
		card.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return card;
	}
}
