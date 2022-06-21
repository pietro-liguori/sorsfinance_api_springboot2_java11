package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.dto.CategoryDTO;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "name" })
public class Category extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Asset> assets = new HashSet<>();
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Transaction> transactions = new HashSet<>();

	public Category() {
		super();
	}

	public Category(Long id) {
		super(id, Category.class);
	}

	public Category(Long id, String name) {
		super(id, Category.class);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Category setName(String name) {
		this.name = name;
		return this;
	}

	public Set<Asset> getAssets() {
		return assets;
	}

	public Category addAsset(Asset asset) {
		assets.add(asset);
		return this;
	}

	public Category addAssets(Asset... assets) {
		for (Asset x : assets) {
			this.assets.add(x);
		}
		return this;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public Category addTransaction(Transaction transaction) {
		transactions.add(transaction);
		return this;
	}
	
	public Category addTransactions(Transaction... transactions) {
		for (Transaction x : transactions) {
			this.transactions.add(x);
		}
		return this;
	}
	
	public static Category fromDTO(CategoryDTO dto) {
		Category cat = new Category(dto.getId(), dto.getName());
		cat.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		return cat;
	}
}
