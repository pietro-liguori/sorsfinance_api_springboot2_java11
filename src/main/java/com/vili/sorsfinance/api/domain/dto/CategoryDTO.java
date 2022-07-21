package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.validation.constraints.ValidCategory;
import com.vili.sorsfinance.framework.DataTransferObject;

public class CategoryDTO extends DataTransferObject {

	@ValidCategory
	private String name;

	public CategoryDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public CategoryDTO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public Category toEntity() {
		return new Category(getId(), getName());
	}
}
