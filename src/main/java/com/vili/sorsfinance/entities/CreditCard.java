package com.vili.sorsfinance.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.vili.sorsfinance.entities.enums.CardStatus;
import com.vili.sorsfinance.entities.enums.CardType;
import com.vili.sorsfinance.entities.enums.PeriodUnit;

@Entity
public class CreditCard extends Card{

	private static final long serialVersionUID = 1L;

	private Integer closingDay;
	private Integer gracePeriod;
	private PeriodUnit gracePeriodUnit;
	private Double interest;
	private PeriodUnit interestUnit;
	
	public CreditCard() {
	}

	public CreditCard(Long id, String name, Account account, String number, Date expiration, CardType type,
			CardStatus status, Integer closingDay, Integer gracePeriod,PeriodUnit gracePeriodUnit, Double interest, PeriodUnit interestUnit) {
		super(id, name, account, number, expiration, type, status);
		this.closingDay = closingDay;
		this.gracePeriod = gracePeriod;
		this.gracePeriodUnit = gracePeriodUnit;
		this.interest = interest;
		this.interestUnit = interestUnit;
	}

	public Integer getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(Integer closingDay) {
		this.closingDay = closingDay;
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public PeriodUnit getGracePeriodUnit() {
		return gracePeriodUnit;
	}

	public void setGracePeriodUnit(PeriodUnit gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public PeriodUnit getInterestUnit() {
		return interestUnit;
	}

	public void setInterestUnit(PeriodUnit interestUnit) {
		this.interestUnit = interestUnit;
	}
}
