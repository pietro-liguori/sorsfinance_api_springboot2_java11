package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.TransactionType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "date", "description", "type", "categories", "recipient", "payments", "items", "discount" })
public class Transaction extends BusEntity {

	private static final long serialVersionUID = 1L;

	private Date date;
	private String description;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double discount;
	private Integer type;
	@ManyToOne
	@JoinColumn(name = "recipientId")
	private Person recipient;
	@ManyToMany
	@JoinTable(name = "transaction_category", joinColumns = @JoinColumn(name = "transaction_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	@OneToMany(mappedBy = "transaction")
	@JsonIgnoreProperties({ "transaction" })
	private Set<TransactionItem> items = new HashSet<>();
	@OneToMany(mappedBy = "transaction")
	@JsonIgnoreProperties({ "transaction" })
	private Set<Payment> payments = new HashSet<>();

	public Transaction() {
	}

	public Transaction(Long id, Person recipient, Date date, String description, Double discount, TransactionType type) {
		super(id, Transaction.class);
		this.recipient = recipient;
		this.date = date;
		this.description = description;
		this.discount = discount;
		this.type = type.getCode();
	}

	public Date getDate() {
		return date;
	}

	public Transaction setDate(Date date) {
		this.date = date;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Transaction setDescription(String description) {
		this.description = description;
		return this;
	}

	public Double getDiscount() {
		return discount;
	}

	public Transaction setDiscount(Double discount) {
		this.discount = discount;
		return this;
	}

	public String getType() {
		return TransactionType.toEnum(type).getLabel();
	}

	public Transaction setType(TransactionType type) {
		this.type = type.getCode();
		return this;
	}

	public Set<TransactionItem> getItems() {
		return items;
	}

	public Transaction addItem(TransactionItem item) {
		items.add(item);
		return this;
	}

	public Transaction addItems(TransactionItem... items) {
		for (TransactionItem x : items) {
			this.items.add(x);
		}
		return this;
	}

	public Person getRecipient() {
		return recipient;
	}

	public Transaction setRecipient(Person recipient) {
		this.recipient = recipient;
		return this;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public Transaction addCategory(Category category) {
		categories.add(category);
		return this;
	}

	public Transaction addCategories(Category... categories) {
		for (Category x : categories) {
			this.categories.add(x);
		}
		return this;
	}
	
	public Set<Payment> getPayments() {
		return payments;
	}
	
	public Transaction addPayment(Payment payment) {
		payments.add(payment);
		return this;
	}
	
	public Transaction addPayments(Payment... payments) {
		for (Payment x : payments) {
			this.payments.add(x);
		}
		return this;
	}
}
