package com.vili.sorsfinance.api.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "deliveryDate", "brand", "expirationDate","type", "categories" })
public class Asset extends BusEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer type;
	@ManyToMany
	@JoinTable(name = "asset_category", joinColumns = @JoinColumn(name = "asset_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "asset")
	@JsonIgnoreProperties({ "asset" })
	private Set<TransactionItem> items = new HashSet<>();

	public Asset() {
		super();
	}

	public Asset(Long id, String name, AssetType type, Class<?> sorsClass) {
		super(id, sorsClass);
		this.name = name;
		this.type = type.getCode();
	}

	public String getName() {
		return name;
	}

	public Asset setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return AssetType.toEnum(type).getLabel();
	}

	public Asset setType(AssetType type) {
		this.type = type.getCode();
		return this;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public Asset addCategory(Category category) {
		categories.add(category);
		return this;
	}

	public Asset addCategories(Category... categories) {
		for (Category x : categories) {
			this.categories.add(x);
		}
		return this;
	}

	public Set<TransactionItem> getItems() {
		return items;
	}
	
	public Asset addItem(TransactionItem item) {
		items.add(item);
		return this;
	}

	public Asset addItems(TransactionItem... items) {
		for (TransactionItem x : items) {
			this.items.add(x);
		}
		return this;
	}

	@JsonIgnore
	public List<Transaction> getTransactions() {
		List<Transaction> list = new ArrayList<>();
		for (TransactionItem x : items) {
			list.add(x.getTransaction());
		}
		return list;
	}
}
