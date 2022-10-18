package com.vili.sorsfinance.api.domain.dto;

import java.util.List;
import java.util.Set;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.Product;
import com.vili.sorsfinance.api.domain.ServiceProvision;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.validation.constraints.ValidAsset;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidAsset
public class AssetDTO extends DataTransferObject {

	private String name;
	private Integer type;
	private Set<Long> categoryIds;
	private String brand;
	private String description;

	public AssetDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Long> getCategoryIds() {
		return categoryIds.stream().toList();
	}

	public void setCategoryIds(Set<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Asset toEntity() {
		AssetType type = AssetType.toEnum(getType());

		if (AssetType.PRODUCT.equals(type)) {
			Product prod = new Product(getId(), getName(), getBrand());

			for (Long categoryId : getCategoryIds()) {
				prod.addCategory(new Category(categoryId));
			}
			
			return prod;
		}
		
		if (AssetType.SERVICE_PROVISION.equals(type)) {
			ServiceProvision prod = new ServiceProvision(getId(), getName(), getDescription());

			for (Long categoryId : getCategoryIds()) {
				prod.addCategory(new Category(categoryId));
			}
			
			return prod;
		}

		return null;
	}
}
