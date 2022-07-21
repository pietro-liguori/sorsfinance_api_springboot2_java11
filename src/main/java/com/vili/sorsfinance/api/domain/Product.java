package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.repositories.ProductRepository;
import com.vili.sorsfinance.api.services.ProductService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(ProductService.class)
@RepositoryRef(ProductRepository.class)
public class Product extends Asset {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expirationDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String brand;

	public Product() {
		super();
	}

	public Product(Long id, String name, AssetType type, Date expirationDate, String brand) {
		super(id, name, type, Product.class);
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
