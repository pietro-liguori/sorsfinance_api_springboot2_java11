package com.vili.sorsfinance.entities;

import java.io.Serializable;
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

import com.vili.sorsfinance.entities.enums.TransactionType;

@Entity
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String description;
	private Double discount;
	private Integer type;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@OneToMany(mappedBy = "id.transaction")
	private Set<TransactionItem> items = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "recipientOrPayerId")
	private Person recipientOrPayer;
	@OneToMany(mappedBy = "transaction")
	private Set<Payment> payments = new HashSet<>();

	public Transaction() {
	}

	public Transaction(Long id, Person recipientOrPayer, Date date, String description, Double discount,
			Category category, TransactionType type) {
		super();
		this.id = id;
		this.recipientOrPayer = recipientOrPayer;
		this.date = date;
		this.description = description;
		this.discount = discount;
		this.category = category;
		this.type = type.getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TransactionType getType() {
		return TransactionType.toEnum(type);
	}

	public void setType(TransactionType type) {
		this.type = type.getCode();
	}

	public Set<TransactionItem> getItems() {
		return items;
	}

	public void addItem(TransactionItem transactionItem) {
		items.add(transactionItem);
	}

	public void addItems(List<TransactionItem> transactionItems) {
		for (TransactionItem x : transactionItems) {
			items.add(x);
		}
	}

	public Person getRecipientOrPayer() {
		return recipientOrPayer;
	}

	public void setRecipientOrPayer(Person recipientOrPayer) {
		this.recipientOrPayer = recipientOrPayer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Payment> getPayments() {
		return payments;
	}
	
	public void addPayment(Payment payment) {
		payments.add(payment);
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
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}
}
