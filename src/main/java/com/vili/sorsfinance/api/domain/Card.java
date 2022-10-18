package com.vili.sorsfinance.api.domain;

import java.util.Arrays;
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
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(CardService.class)
@RepositoryRef(CardRepository.class)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "printedName", "number", "expiration", "type", "closingDay", "gracePeriod", "gracePeriodUnit",
		"interest", "interestUnit", "balance", "threshold", "status", "account" })
public class Card extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	public static final List<CardType> CREDIT_CARD_TYPES = Arrays.asList(CardType.CREDIT, CardType.MULTIPLE, CardType.ONLINE);

	public static final List<CardType> BANK_CARD_TYPES = Arrays.asList(CardType.CREDIT, CardType.DEBIT, CardType.MULTIPLE, CardType.ONLINE);

	public static final List<CardType> VOUCHER_TYPES = Arrays.asList(CardType.FOOD_TICKET, CardType.MEAL_TICKET, CardType.FUEL_TICKET, CardType.TRANSPORT_TICKET);

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "cards", "vouchers" })
	private Account account;

	public Card() {
		super();
	}

	public Card(Long id) {
		super(id, Card.class);
	}

	protected Card(Long id, Class<?> domain) {
		super(id, domain);
	}

	protected Card(Long id, String name, Account account, String number, CardType type, CardStatus status, Class<?> domain) {
		super(id, domain);
		this.name = name;
		this.account = account;
		this.number = number;
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

	public Integer getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type == null ? null : type.getCode();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status == null ? null : status.getCode();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
