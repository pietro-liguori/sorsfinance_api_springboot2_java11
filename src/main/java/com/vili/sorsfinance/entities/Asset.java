package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.AssetType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Asset implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer type;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "id.asset")
	private Set<TransactionItem> items = new HashSet<>();

	public Asset() {
	}

	public Asset(Long id, String description, AssetType type, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.type = type.getCode();
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AssetType getType() {
		return AssetType.toEnum(type);
	}

	public void setType(AssetType type) {
		this.type = type.getCode();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<TransactionItem> getItems() {
		return items;
	}
	
	public void addItems(TransactionItem transactionItem) {
		items.add(transactionItem);
	}

	public void addItems(List<TransactionItem> transactionItems) {
		for (TransactionItem x : transactionItems ) {
			items.add(x);
		}
	}

	@JsonIgnore
	public List<Transaction> getTransactions() {
		List<Transaction> list = new ArrayList<>();
		for (TransactionItem x : items) {
			list.add(x.getTransaction());
		}
		return list;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		return Objects.equals(id, other.id);
	}
}
