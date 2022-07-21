package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.repositories.AssetRepository;
import com.vili.sorsfinance.api.services.AssetService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

@Entity
@ServiceRef(value = AssetService.class)
@RepositoryRef(value = AssetRepository.class)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "brand", "deliveryDate", "expirationDate", "type", "categories" })
public class Asset extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@ManyToMany
	@JoinTable(name = "asset_category", joinColumns = @JoinColumn(name = "asset_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@FilterSetting(alias = "category", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "asset")
	@JsonIgnore
	private Set<TransactionItem> items = new HashSet<>();

	public Asset() {
		super();
	}

	public Asset(Long id) {
		super(id, Asset.class);
	}

	public Asset(Long id, String name, AssetType type, Class<?> domain) {
		super(id, domain);
		this.name = name;
		this.type = type.getCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		try {
			return AssetType.toEnum(type).getLabel();
		} catch (EnumValueNotFoundException e) {
			return null;
		}
	}

	public void setType(AssetType type) {
		this.type = type.getCode();
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void addCategories(Category... categories) {
		for (Category x : categories) {
			this.categories.add(x);
		}
	}

	public Set<TransactionItem> getItems() {
		return items;
	}
	
	public void addItem(TransactionItem item) {
		items.add(item);
	}

	public void addItems(TransactionItem... items) {
		for (TransactionItem x : items) {
			this.items.add(x);
		}
	}
}