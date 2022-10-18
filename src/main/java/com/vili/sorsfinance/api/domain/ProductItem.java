package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.MeasurementType;
import com.vili.sorsfinance.api.domain.enums.MeasurementUnit;
import com.vili.sorsfinance.api.domain.enums.TransactionItemType;
import com.vili.sorsfinance.api.repositories.ProductItemRepository;
import com.vili.sorsfinance.api.services.ProductItemService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(ProductItemService.class)
@RepositoryRef(ProductItemRepository.class)
public class ProductItem extends TransactionItem {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double content;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer contentType;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer contentUnit;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expirationDate;

	public ProductItem() {
		super();
		super.setType(TransactionItemType.PRODUCT_ITEM);
	}
	
	public ProductItem(Long id) {
		super(id, ProductItem.class);
		super.setType(TransactionItemType.PRODUCT_ITEM);
	}
	
	public ProductItem(Long id, Transaction transaction, Asset asset, Double price, Integer quantity, Double discount, Date deliveryDate, Double content, MeasurementType contentType, MeasurementUnit contentUnit, Date expirationDate) {
		super(id, transaction, asset, TransactionItemType.PRODUCT_ITEM, price, quantity, discount, deliveryDate, ProductItem.class);
		this.content = content;
		this.contentType = contentType == null ? null : contentType.getCode();
		this.contentUnit = contentUnit == null ? null : contentUnit.getCode();
		this.expirationDate = expirationDate;
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

	public void setContentType(MeasurementType contentType) {
		this.contentType = contentType == null ? null : contentType.getCode();
	}

	public Integer getContentUnit() {
		return contentUnit;
	}

	public void setContentUnit(MeasurementUnit contentUnit) {
		this.contentUnit = contentUnit == null ? null : contentUnit.getCode();
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
