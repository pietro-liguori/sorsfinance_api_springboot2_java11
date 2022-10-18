package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.CardStatus;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.repositories.CreditCardRepository;
import com.vili.sorsfinance.api.services.CreditCardService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(CreditCardService.class)
@RepositoryRef(CreditCardRepository.class)
public class CreditCard extends BankCard {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer closingDay;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer gracePeriod;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer gracePeriodUnit;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double interest;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer interestUnit;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double threshold;

	public CreditCard() {
		super();
	}

	public CreditCard(Long id) {
		super(id, CreditCard.class);
	}

	public CreditCard(Long id, String name, Account account, String printedName, String number, Date expiration, Integer closingDay, Integer gracePeriod, PeriodUnit gracePeriodUnit, Double interest,
			PeriodUnit interestUnit, Double threshold, CardType type, CardStatus status) {
		super(id, name, account, printedName, number, expiration, type, status, CreditCard.class);
		this.closingDay = closingDay;
		this.gracePeriod = gracePeriod;
		this.gracePeriodUnit = gracePeriodUnit == null ? null : gracePeriodUnit.getCode();
		this.interest = interest;
		this.interestUnit = interestUnit == null ? null : interestUnit.getCode();
		this.threshold = threshold;
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

	public Integer getGracePeriodUnit() {
		return gracePeriodUnit;
	}

	public void setGracePeriodUnit(PeriodUnit gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit == null ? null : gracePeriodUnit.getCode();
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

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}
}
