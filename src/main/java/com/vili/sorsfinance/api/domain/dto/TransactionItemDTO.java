package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.framework.DataTransferObject;

public class TransactionItemDTO extends DataTransferObject {

	private Double price;
	private Integer quantity;
	private Double discount;
	private Long assetId;
	private Long transactionId;
	
	public TransactionItemDTO() {
		super();
	}

	public Double getPrice() {
		return price;
	}

	public TransactionItemDTO setPrice(Double price) {
		this.price = price;
		return this;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public TransactionItemDTO setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public Double getDiscount() {
		return discount;
	}

	public TransactionItemDTO setDiscount(Double discount) {
		this.discount = discount;
		return this;
	}

	public Long getAssetId() {
		return assetId;
	}

	public TransactionItemDTO setAssetId(Long assetId) {
		this.assetId = assetId;
		return this;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public TransactionItemDTO setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	
	@Override
	public TransactionItem toEntity() {
		Asset asset = new Asset(getAssetId());
		Transaction trans = new Transaction(getTransactionId());
		TransactionItem item = new TransactionItem(getId(), trans, asset, getPrice(),
				getQuantity(), getDiscount());
		return item;
	}
}
