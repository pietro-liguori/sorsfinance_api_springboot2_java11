package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.CategoryType;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer tier;
	private Integer type;
	@ManyToOne
	@JsonIgnore
	private Category parent;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Category> children = new HashSet<>();
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private Set<Asset> assets = new HashSet<>();

	public Category() {
	}

	public Category(Long id, String name, Integer tier, CategoryType type) {
		super();
		this.id = id;
		this.name = name;
		this.tier = tier;
		this.type = type.getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTier() {
		return tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}

	public CategoryType getType() {
		return CategoryType.toEnum(type);
	}

	public void setType(CategoryType type) {
		if (type != null) {
			this.type = type.getCode();
		}
	}

	@JsonIgnore
	public Category getParent() {
		if (tier == 1) {
			return null;
		}
		return parent;
	}

	public void setParent(Category parent) {
		if (tier > 1) {
			this.parent = parent;
		}
	}

	public Set<Category> getChildren() {
		if (tier == 4) {
			return null;
		}
		return children;
	}

	public void addChild(Category child) {
		if (tier < 4) {
			children.add(child);
		}
	}

	public Set<Asset> getAssets() {
		return assets;
	}

	public void addAsset(Asset asset) {
		if (tier < 4) {
			assets.add(asset);
		}
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
}
