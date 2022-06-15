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

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getType() {
		return TransactionType.toEnum(type).getLabel();
	}

	public void setType(TransactionType type) {
		this.type = type.getCode();
	}

	public Set<TransactionItem> getItems() {
		return items;
	}

	public void addItem(TransactionItem item) {
		items.add(item);
	}

	public void addItems(TransactionItem... items) {
		for (TransactionItem x : items) {
			this.items.add(x);
		}
	}

	public Person getRecipient() {
		return recipient;
	}

	public void setRecipient(Person recipient) {
		this.recipient = recipient;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void addCategories(Category... categories) {
		for (Category x : categories) {
			this.categories.add(x);
		}
	}
	
	public Set<Payment> getPayments() {
		return payments;
	}
	
	public void addPayment(Payment payment) {
		payments.add(payment);
	}
	
	public void addPayments(Payment... payments) {
		for (Payment x : payments) {
			this.payments.add(x);
		}
	}
}
