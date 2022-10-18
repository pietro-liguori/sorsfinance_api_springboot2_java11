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
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.repositories.PaymentRepository;
import com.vili.sorsfinance.api.services.PaymentService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(value = PaymentService.class)
@RepositoryRef(value = PaymentRepository.class)
@Inheritance(strategy=InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "description", "type", "value", "installments", "status", "responsible", "account", "card", "transaction" })
public class Payment extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	public static final List<PaymentType> INSTALLMENT_PAYMENT_TYPES = Arrays.asList(PaymentType.CREDIT, PaymentType.BANK_SLIP, PaymentType.CHECK);
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double value;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "cards" })
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "responsible_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "contact", "category", "recipient", "payments", "discount", "type" })
	private Person responsible;
	
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	@FilterSetting(nesting = { "id" })
	@FilterSetting(alias = "date", nesting = { "date" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "items", "category", "recipient", "payments", "discount", "type" })
	private Transaction transaction;
	
	@ManyToOne
	@JoinColumn(name = "card_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "account", "holder" })
	private Card card;
	
	public Payment() {
		super();
	}

	public Payment(Long id) {
		super(id, Payment.class);
	}

	public Payment(Long id, String description, PaymentType type, Double value, PaymentStatus status, Account account, Person responsible,
			Transaction transaction, Card card) {
		super(id, Payment.class);
		this.description = description;
		this.type = type == null ? null : type.getCode();
		this.value = value;
		this.status = status == null ? null : status.getCode();
		this.account = account;
		this.responsible = responsible;
		this.transaction = transaction;
		this.card = card;
	}

	protected Payment(Long id, Class<?> domain) {
		super(id, domain);
	}

	protected Payment(Long id, String description, PaymentType type, Double value, PaymentStatus status, Account account, Person responsible,
			Transaction transaction, Card card, Class<?> domain) {
		super(id, domain);
		this.description = description;
		this.type = type == null ? null : type.getCode();
		this.value = value;
		this.status = status == null ? null : status.getCode();
		this.account = account;
		this.responsible = responsible;
		this.transaction = transaction;
		this.card = card;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type == null ? null : type.getCode();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status == null ? null : status.getCode();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Person getResponsible() {
		return responsible;
	}

	public void setResponsible(Person responsible) {
		this.responsible = responsible;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
