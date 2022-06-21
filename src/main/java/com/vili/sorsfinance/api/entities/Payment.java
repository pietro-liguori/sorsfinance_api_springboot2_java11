package com.vili.sorsfinance.api.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.PaymentStatus;
import com.vili.sorsfinance.api.entities.enums.PaymentType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "description", "type", "value", "installments", "status", "responsible", "account", "card", "transaction" })
public class Payment extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String description;
	private Integer type;
	private Double value;
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnoreProperties({ "cards" })
	private Account account;
	@ManyToOne
	@JoinColumn(name = "responsible_id")
	@JsonIgnoreProperties({ "contact", "category", "recipientOrPayer", "payments", "discount", "type" })
	private Person responsible;
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	@JsonIgnoreProperties({ "items", "category", "recipientOrPayer", "payments", "discount", "type" })
	private Transaction transaction;
	@ManyToOne
	@JoinColumn(name = "card_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "account", "holder" })
	private Card card;
	
	public Payment() {
		super();
	}

	public Payment(Long id, String description, PaymentType type, Double value, PaymentStatus status, Account account, Person responsible,
			Transaction transaction, Card card) {
		super(id, Payment.class);
		this.description = description;
		this.type = type.getCode();
		this.value = value;
		this.status = status.getCode();
		this.account = account;
		this.responsible = responsible;
		this.transaction = transaction;
		this.card = card;
	}

	public Payment(Long id, String description, PaymentType type, Double value, PaymentStatus status, Account account, Person responsible,
			Transaction transaction, Card card, Class<?> sorsClass) {
		super(id, sorsClass);
		this.description = description;
		this.type = type.getCode();
		this.value = value;
		this.status = status.getCode();
		this.account = account;
		this.responsible = responsible;
		this.transaction = transaction;
		this.card = card;
	}

	public String getDescription() {
		return description;
	}

	public Payment setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getType() {
		return PaymentType.toEnum(type).getLabel();
	}

	public Payment setType(PaymentType type) {
		this.type = type.getCode();
		return this;
	}

	public Double getValue() {
		return value;
	}

	public Payment setValue(Double value) {
		this.value = value;
		return this;
	}

	public String getStatus() {
		return PaymentStatus.toEnum(status).getLabel();
	}

	public Payment setStatus(PaymentStatus status) {
		this.status = status.getCode();
		return this;
	}

	public Account getAccount() {
		return account;
	}

	public Payment setAccount(Account account) {
		this.account = account;
		return this;
	}

	public Person getResponsible() {
		return responsible;
	}

	public Payment setResponsible(Person responsible) {
		this.responsible = responsible;
		return this;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Payment setTransaction(Transaction transaction) {
		this.transaction = transaction;
		return this;
	}

	public Card getCard() {
		return card;
	}

	public Payment setCard(Card card) {
		this.card = card;
		return this;
	}
}
