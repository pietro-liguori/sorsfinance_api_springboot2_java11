package com.vili.sorsfinance.api.entities.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import com.vili.sorsfinance.api.entities.Asset;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.UniqueAsset;
import com.vili.sorsfinance.validation.constraints.ValidCategoryId;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;

public class AssetDTO extends DTO<Asset> {

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
}
