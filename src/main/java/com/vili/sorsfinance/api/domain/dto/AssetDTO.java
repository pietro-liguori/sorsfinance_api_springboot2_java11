package com.vili.sorsfinance.api.domain.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.Product;
import com.vili.sorsfinance.api.domain.ServiceProvision;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.validation.constraints.UniqueAsset;
import com.vili.sorsfinance.api.validation.constraints.ValidCategoryId;
import com.vili.sorsfinance.api.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.framework.DataTransferObject;

public class AssetDTO extends DataTransferObject {

	@UniqueAsset
	private String name;
	@ValidEnumValue(target = AssetType.class)
	private Integer type;
	@NotEmpty(message = "Must not be null or empty")
	private Set<@ValidCategoryId Long> categoryIds;
	@Future
	private Date expirationDate;
	private String brand;
	@Future
	private Date deliveryDate;

	public AssetDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public AssetDTO setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public AssetDTO setType(Integer type) {
		this.type = type;
		return this;
	}

	public Set<Long> getCategoryIds() {
		return categoryIds;
	}

	public AssetDTO setCategoryIds(Set<Long> categoryIds) {
		this.categoryIds = categoryIds;
		return this;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Override
	public Asset toEntity() {
		AssetType type = AssetType.toEnum(getType());

		if (AssetType.PRODUCT.equals(type)) {
			Product prod = new Product(getId(), getName(), AssetType.toEnum(getType()), getExpirationDate(), getBrand());

			for (Long categoryId : getCategoryIds()) {
				prod.addCategory(new Category(categoryId));
			}
			
			return prod;
		}
		
		if (AssetType.SERVICE_PROVISION.equals(type)) {
			ServiceProvision prod = new ServiceProvision(getId(), getName(), AssetType.toEnum(getType()), getDeliveryDate());

			for (Long categoryId : getCategoryIds()) {
				prod.addCategory(new Category(categoryId));
			}
			
			return prod;
		}

		return null;
	}
}
