package com.vili.sorsfinance.api.domain;

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
	private String brand;
	
	public Product() {
		super();
		super.setType(AssetType.PRODUCT);
	}

	public Product(Long id) {
		super(id, Product.class);
		super.setType(AssetType.PRODUCT);
	}

	public Product(Long id, String name, String brand) {
		super(id, name, AssetType.PRODUCT, Product.class);
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
