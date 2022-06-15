package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.entities.enums.AssetType;

@Entity
public class Product extends Asset {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expirationDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String brand;

	public Product() {
	}

	public Product(Long id, String description, AssetType type, Date expirationDate, String brand) {
		super(id, description, type, Product.class);
		this.expirationDate = expirationDate;
		this.brand = brand;
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
}
