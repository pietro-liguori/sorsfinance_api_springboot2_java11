package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.entities.dto.AssetDTO;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.framework.DTOType;

@Entity
public class Product extends Asset {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expirationDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String brand;

	public Product() {
	}

	public Product(Long id, String name, AssetType type, Date expirationDate, String brand) {
		super(id, name, type, Product.class);
		this.expirationDate = expirationDate;
		this.brand = brand;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public Product setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
		return this;
	}

	public String getBrand() {
		return brand;
	}

	public Product setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public static Product fromDTO(AssetDTO dto) {
		Product obj = new Product(dto.getId(), dto.getName(), AssetType.toEnum(dto.getType()), dto.getExpirationDate(),
				dto.getBrand());

		for (Long categoryId : dto.getCategoryIds()) {
			obj.addCategory(new Category(categoryId));
		}

		if (dto.getMethod().equals(DTOType.INSERT))
			obj.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		obj.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return obj;
	}
}
