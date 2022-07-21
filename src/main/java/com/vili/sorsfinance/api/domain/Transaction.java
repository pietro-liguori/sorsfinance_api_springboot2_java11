package com.vili.sorsfinance.api.domain;

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
import com.vili.sorsfinance.api.domain.enums.TransactionType;
import com.vili.sorsfinance.api.repositories.TransactionRepository;
import com.vili.sorsfinance.api.services.TransactionService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(TransactionService.class)
@RepositoryRef(TransactionRepository.class)
@JsonPropertyOrder({ "id", "date", "description", "total", "discount", "type", "categories", "recipient", "payments", "items" })
public class Transaction extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date date;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double discount;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double total;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@ManyToOne
	@JoinColumn(name = "recipientId")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Person recipient;
	
	@ManyToMany
	@JoinTable(name = "transaction_category", joinColumns = @JoinColumn(name = "transaction_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@FilterSetting(alias = "category", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "transaction")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "transaction" })
	private Set<TransactionItem> items = new HashSet<>();
	
	@OneToMany(mappedBy = "transaction")
	@FilterSetting(alias = "account", nesting = { "account", "id" })
	@FilterSetting(alias = "card", nesting = { "card", "id" })
	@FilterSetting(alias = "responsible", nesting = { "responsible", "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "transaction" })
	private Set<Payment> payments = new HashSet<>();

	public Transaction() {
		super();
	}
	
	public Transaction(Long id) {
		super(id, Transaction.class);
	}

	public Transaction(Long id, Person recipient, Date date, String description, Double total, Double discount,
			TransactionType type) {
		super(id, Transaction.class);
		this.recipient = recipient;
		this.date = date;
		this.description = description;
		this.discount = discount;
		this.total = total;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	public void addPayments(Payment... payments) {
		for (Payment x : payments) {
			this.payments.add(x);
		}
	}
}
