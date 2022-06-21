package com.vili.sorsfinance.api.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.CardDTO;
import com.vili.sorsfinance.api.entities.enums.CardStatus;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "number", "expiration", "type", "closingDay", "gracePeriod", "gracePeriodUnit",
		"interest", "interestUnit", "status", "account" })
public class Card extends BusEntity {

	private static final long serialVersionUID = 1L;

	public static final List<CardType> CREDIT_CARD_TYPES = Arrays.asList(CardType.CREDIT, CardType.MULTIPLE,
			CardType.ONLINE);

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

	public Card(Long id, String name, Account account, String number, Date expiration, CardType type,
			CardStatus status) {
		super(id, Card.class);
		this.name = name;
		this.account = account;
		this.number = number;
		this.expiration = expiration;
		this.type = type.getCode();
		this.status = status.getCode();
	}

	public Card(Long id, String name, Account account, String number, Date expiration, CardType type, CardStatus status,
			Class<?> sorsClass) {
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

	public Card setName(String name) {
		this.name = name;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public Card setNumber(String number) {
		this.number = number;
		return this;
	}

	public Date getExpiration() {
		return expiration;
	}

	public Card setExpiration(Date expiration) {
		this.expiration = expiration;
		return this;
	}

	public String getType() {
		return CardType.toEnum(type).getLabel();
	}

	public Card setType(CardType type) {
		this.type = type.getCode();
		return this;
	}

	public String getStatus() {
		return CardStatus.toEnum(status).getLabel();
	}

	public Card setStatus(CardStatus status) {
		this.status = status.getCode();
		return this;
	}

	public Account getAccount() {
		return account;
	}

	public Card setAccount(Account account) {
		this.account = account;
		return this;
	}

	public static Card fromDTO(CardDTO dto) {
		Account acc = new Account(dto.getAccountId());
		Card card = new Card(dto.getId(), dto.getName(), acc, dto.getNumber(), dto.getExpiration(),
				CardType.toEnum(dto.getType()), CardStatus.toEnum(dto.getStatus()));
		card.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return card;
	}
}
