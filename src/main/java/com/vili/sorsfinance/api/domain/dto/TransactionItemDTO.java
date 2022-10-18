package com.vili.sorsfinance.api.domain.dto;

import java.util.Date;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.ProductItem;
import com.vili.sorsfinance.api.domain.ServiceProvisionItem;
import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.api.domain.enums.MeasurementType;
import com.vili.sorsfinance.api.domain.enums.MeasurementUnit;
import com.vili.sorsfinance.api.domain.enums.TransactionItemType;
import com.vili.sorsfinance.framework.DataTransferObject;

public class TransactionItemDTO extends DataTransferObject {

	private Double price;
	private Integer quantity;
	private Double discount;
	private Date deliveryDate;
	private Date expirationDate;
	private Integer type;
	private AssetDTO asset;
	private Long transactionId;
	private Double content;
	private Integer contentType;
	private Integer contentUnit;
	private PersonDTO provider;

	public TransactionItemDTO() {
		super();
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Long getTransactionId() {
		return transactionId;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getContent() {
		return content;
	}

	public void setContent(Double content) {
		this.content = content;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Integer getContentUnit() {
		return contentUnit;
	}

	public void setContentUnit(Integer contentUnit) {
		this.contentUnit = contentUnit;
	}

	public PersonDTO getProvider() {
		return provider;
	}

	public void setProvider(PersonDTO provider) {
		this.provider = provider;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	public AssetDTO getAsset() {
		return asset;
	}

	public void setAsset(AssetDTO asset) {
		this.asset = asset;
	}

	@Override
	public TransactionItem toEntity() {
		Asset asset = getAsset().toEntity();
		TransactionItemType type = TransactionItemType.toEnum(getType());
		Transaction trans = new Transaction(getTransactionId());
		TransactionItem item = null;
		
		switch (type) {
		case PRODUCT_ITEM:
			item = new ProductItem(getId(), trans, asset, getPrice(),
					getQuantity(), getDiscount(), getDeliveryDate(), getContent(), MeasurementType.toEnum(getContentType()), MeasurementUnit.toEnum(getContentUnit()), getExpirationDate());
			break;
		case SERVICE_PROVISION_ITEM:
			Person provider = new NaturalPerson(getProvider().getId());
			item = new ServiceProvisionItem(getId(), trans, asset, getPrice(),
					getQuantity(), getDiscount(), getDeliveryDate(), provider);
			break;
		}

		return item;
	}
}
