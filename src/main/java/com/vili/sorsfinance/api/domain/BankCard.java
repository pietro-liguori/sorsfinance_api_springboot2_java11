package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.CardStatus;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.repositories.BankCardRepository;
import com.vili.sorsfinance.api.services.BankCardService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(BankCardService.class)
@RepositoryRef(BankCardRepository.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class BankCard extends Card {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String printedName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expiration;

	public BankCard() {
		super();
	}

	public BankCard(Long id) {
		super(id, BankCard.class);
	}

	public BankCard(Long id, String name, Account account, String printedName, String number, Date expiration, CardType type,
			CardStatus status) {
		super(id, name, account, number, type, status, BankCard.class);
		this.printedName = printedName;
		this.expiration = expiration;
	}

	protected BankCard(Long id, Class<?> domain) {
		super(id, domain);
	}
	
	protected BankCard(Long id, String name, Account account, String printedName, String number, Date expiration, CardType type,
			CardStatus status, Class<?> domain) {
		super(id, name, account, number, type, status, domain);
		this.printedName = printedName;
		this.expiration = expiration;
	}

	public String getPrintedName() {
		return printedName;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
