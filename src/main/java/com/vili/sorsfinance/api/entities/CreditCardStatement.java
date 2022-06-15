package com.vili.sorsfinance.api.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.PaymentStatus;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "description", "closingDate", "dueDate", "installments", "payDate", "status", "payment", "card", "items" })
public class CreditCardStatement extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String description;
	private Date closingDate;
	private Date dueDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date payDate;
	private Integer status;
	@OneToOne
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name = "card_id")
	private CreditCard card;
	@OneToMany(mappedBy = "statement")
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

	@JsonIgnore
	public List<Transaction> getTransaction() {
		List<Transaction> list = new ArrayList<>();
		for (CreditInstallment x : items) {
			list.add(x.getPayment().getTransaction());
		}
		return list;
	}
}
