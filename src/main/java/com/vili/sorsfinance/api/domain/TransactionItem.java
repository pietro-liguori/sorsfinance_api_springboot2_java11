package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.TransactionItemType;
import com.vili.sorsfinance.api.repositories.TransactionItemRepository;
import com.vili.sorsfinance.api.services.TransactionItemService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(TransactionItemService.class)
@RepositoryRef(TransactionItemRepository.class)
@JsonPropertyOrder({ "id", "price", "quantity", "discount", "asset", "payments", "transaction" })
public abstract class TransactionItem extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double price;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer quantity;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double discount;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date deliveryDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;

	@ManyToOne
	@JoinColumn(name = "asset_id")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "items" })
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "items", "payments" })
	private Transaction transaction;
	
	protected TransactionItem() {
		super();
	}
	
	protected TransactionItem(Long id, Class<?> domain) {
		super(id, domain);
	}

	protected TransactionItem(Long id, Transaction transaction, Asset asset, TransactionItemType type, Double price, Integer quantity, Double discount, Date deliveryDate, Class<?> domain) {
		super(id, domain);
		this.asset = asset;
		this.transaction = transaction;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
		this.deliveryDate = deliveryDate;
		this.type = type == null ? null : type.getCode();
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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public Integer getType() {
		return type;
	}

	public void setType(TransactionItemType type) {
		this.type = type == null ? null : type.getCode();
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Asset getAsset() {
		return asset;
	}
	
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
}
