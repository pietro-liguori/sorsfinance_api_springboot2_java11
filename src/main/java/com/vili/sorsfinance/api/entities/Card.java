package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.CardStatus;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "number", "expiration", "type", "closingDay", "gracePeriod", "gracePeriodUnit", "interest", "interestUnit", "status", "account" })
public class Card extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String number;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expiration;
	private Integer type;
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnoreProperties({ "cards" })
	private Account account;
	
	public Card() {
		super();
	}

	public Card(Long id) {
		super(id, Card.class);
	}

	public Card(Long id, String name, Account account, String number, Date expiration, CardType type, CardStatus status) {
		super(id, Card.class);
		this.name = name;
		this.account = account;
		this.number = number;
		this.expiration = expiration;
		this.type = type.getCode();
		this.status = status.getCode();
	}

	public Card(Long id, String name, Account account, String number, Date expiration, CardType type, CardStatus status, Class<?> sorsClass) {
		super(id, sorsClass);
		this.name = name;
		this.account = account;
		this.number = number;
		this.expiration = expiration;
		this.type = type.getCode();
		this.status = status.getCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getType() {
		return CardType.toEnum(type).getLabel();
	}

	public void setType(CardType type) {
		this.type = type.getCode();
	}

	public String getStatus() {
		return CardStatus.toEnum(status).getLabel();
	}

	public void setStatus(CardStatus status) {
		this.status = status.getCode();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
