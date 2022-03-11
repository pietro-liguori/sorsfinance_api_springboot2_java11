package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TransactionItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private TransactionItemPK id = new TransactionItemPK();
	private Double price;
	private Integer quantity;
	private Double discount;
	
	public TransactionItem() {
	}
	
	public TransactionItem(Transaction transaction, Asset asset, Double price, Integer quantity, Double discount) {
		super();
		id.setAsset(asset);
		id.setTransaction(transaction);
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
	}

	public TransactionItemPK getId() {
		return id;
	}

	public void setId(TransactionItemPK id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@JsonIgnore
	public Transaction getTransaction() {
		return id.getTransaction();
	}
	
	public Asset getAsset() {
		return id.getAsset();
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
		TransactionItem other = (TransactionItem) obj;
		return Objects.equals(id, other.id);
	}
}
