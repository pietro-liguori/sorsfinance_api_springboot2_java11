package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.PaymentStatus;

@Entity
public class CreditCardStatement implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Date closingDate;
	private Date dueDate;
	private Date payDate;
	private Integer status;
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name = "card_id")
	private Card card;
	@OneToMany(mappedBy = "id.statement")
	private Set<PaymentInstallment> items = new HashSet<>();

	public CreditCardStatement() {
	}

	public CreditCardStatement(Long id, Card card, String description, Date closingDate, Date dueDate, PaymentStatus status) {
		super();
		this.id = id;
		this.card = card;
		this.description = description;
		this.closingDate = closingDate;
		this.dueDate = dueDate;
		this.status = status.getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Set<PaymentInstallment> getItems() {
		return items;
	}

	public void addItem(PaymentInstallment item) {
		items.add(item);
	}

	public void addItems(List<PaymentInstallment> item) {
		for (PaymentInstallment x : item ) {
			items.add(x);
		}
	}

	@JsonIgnore
	public List<Transaction> getTransaction() {
		List<Transaction> list = new ArrayList<>();
		for (PaymentInstallment x : items) {
			list.add(x.getPayment().getTransaction());
		}
		return list;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCardStatement other = (CreditCardStatement) obj;
		return Objects.equals(id, other.id);
	}
}
