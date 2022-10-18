package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.validation.constraints.ValidCategory;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidCategory
public class CategoryDTO extends DataTransferObject {

	private String name;

	public CategoryDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Category toEntity() {
		return new Category(getId(), getName());
	}
}
