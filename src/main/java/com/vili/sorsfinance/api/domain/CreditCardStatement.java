package com.vili.sorsfinance.api.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.repositories.CreditCardStatementRepository;
import com.vili.sorsfinance.api.services.CreditCardStatementService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(value = CreditCardStatementService.class)
@RepositoryRef(value = CreditCardStatementRepository.class)
@JsonPropertyOrder({ "id", "description", "closingDate", "dueDate", "installments", "payDate", "status", "payment", "card", "items" })
public class CreditCardStatement extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date closingDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date dueDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date payDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "card_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CreditCard card;
	
	@OneToMany(mappedBy = "statement")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "statement" })
	private Set<CreditInstallment> items = new HashSet<>();

	public CreditCardStatement() {
		super();
	}

	public CreditCardStatement(Long id, CreditCard card, String description, Date closingDate, Date dueDate, PaymentStatus status) {
		super(id, CreditCardStatement.class);
		this.card = card;
		this.description = description;
		this.closingDate = closingDate;
		this.dueDate = dueDate;
		this.status = status.getCode();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getStatus() {
		return PaymentStatus.toEnum(status).getLabel();
	}

	public void setStatus(PaymentStatus status) {
		this.status = status.getCode();
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public Set<CreditInstallment> getItems() {
		return items;
	}

	public void addItem(CreditInstallment item) {
		items.add(item);
	}

	public void addItems(CreditInstallment... items) {
		for (CreditInstallment x : items) {
			this.items.add(x);
		}
	}
}
