package com.vili.sorsfinance.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.vili.sorsfinance.entities.enums.AssetType;

@Entity
public class Product extends Asset {

	private static final long serialVersionUID = 1L;
	
	private Date expirationDate;
	private String brand;

	public Product() {
	}

	public Product(Long id, String description, AssetType type, Category category, Date expirationDate, String brand) {
		super(id, description, type, category);
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
