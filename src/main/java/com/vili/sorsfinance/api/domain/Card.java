package com.vili.sorsfinance.api.domain;

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
import com.vili.sorsfinance.api.domain.enums.CardStatus;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.repositories.CardRepository;
import com.vili.sorsfinance.api.services.CardService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

@Entity
@ServiceRef(CardService.class)
@RepositoryRef(CardRepository.class)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "number", "expiration", "type", "closingDay", "gracePeriod", "gracePeriodUnit",
		"interest", "interestUnit", "status", "account" })
public class Card extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	public static final List<CardType> CREDIT_CARD_TYPES = Arrays.asList(CardType.CREDIT, CardType.MULTIPLE, CardType.ONLINE);

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expiration;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
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
			Class<?> domain) {
		super(id, domain);
		this.name = name;
		this.account = account;
		this.number = number;
		this.expiration = expiration;
		this.type = type == null ? null :type.getCode();
		this.status = status == null ? null : status.getCode();
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
		try {
			return CardType.toEnum(type).getLabel();
		} catch (EnumValueNotFoundException e) {
			return null;
		}
	}

	public void setType(CardType type) {
		this.type = type.getCode();
	}

	public String getStatus() {
		try {
			return CardStatus.toEnum(status).getLabel();
		} catch (EnumValueNotFoundException e) {
			return null;
		}
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
