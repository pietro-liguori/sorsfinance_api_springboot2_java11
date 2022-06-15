package com.vili.sorsfinance.api.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "price", "quantity", "discount", "asset", "payments", "transaction" })
public class TransactionItem extends BusEntity{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "asset_id")
	@JsonIgnoreProperties({ "items" })
	private Asset asset;
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	@JsonIgnoreProperties({ "items", "payments" })
	private Transaction transaction;
	private Double price;
	private Integer quantity;
	private Double discount;
	
	public TransactionItem() {
	}
	
	public TransactionItem(Long id, Transaction transaction, Asset asset, Double price, Integer quantity, Double discount) {
		super(id, TransactionItem.class);
		this.asset = asset;
		this.transaction = transaction;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
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
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public Asset getAsset() {
		return asset;
	}

}
