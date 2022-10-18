package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.repositories.CategoryRepository;
import com.vili.sorsfinance.api.services.CategoryService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(CategoryService.class)
@RepositoryRef(CategoryRepository.class)
@JsonPropertyOrder({ "id", "name" })
public class Category extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@NoFilter
	@JsonIgnore
	private Set<Asset> assets = new HashSet<>();
	
	@ManyToMany(mappedBy = "categories")
	@NoFilter
	@JsonIgnore
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

	public void setName(String name) {
		this.name = name;
	}

	public List<Asset> getAssets() {
		return assets.stream().toList();
	}

	public void addAsset(Asset asset) {
		assets.add(asset);
	}

	public void addAssets(Asset... assets) {
		for (Asset x : assets) {
			this.assets.add(x);
		}
	}

	public List<Transaction> getTransactions() {
		return transactions.stream().toList();
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
