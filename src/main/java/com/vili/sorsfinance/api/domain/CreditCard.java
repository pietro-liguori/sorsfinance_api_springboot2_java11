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
public class CreditCard extends Card {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer closingDay;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer gracePeriod;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer gracePeriodUnit = PeriodUnit.DAY.getCode();
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double interest;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer interestUnit = PeriodUnit.MONTH.getCode();

	public CreditCard() {
		super();
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

	public void setClosingDay(Integer closingDay) {
		this.closingDay = closingDay;
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
}
