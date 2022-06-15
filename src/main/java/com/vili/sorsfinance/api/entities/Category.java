package com.vili.sorsfinance.api.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

	public Category(Long id, String name) {
		super(id, Category.class);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Asset> getAssets() {
		return assets;
	}

	public void addAsset(Asset asset) {
		assets.add(asset);
	}

	public void addAssets(Asset... assets) {
		for (Asset x : assets) {
			this.assets.add(x);
		}
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	public void addTransactions(Transaction... transactions) {
		for (Transaction x : transactions) {
			this.transactions.add(x);
		}
	}
}
