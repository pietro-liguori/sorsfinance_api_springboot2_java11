package com.vili.sorsfinance.api.entities.dto;

import com.vili.sorsfinance.api.entities.Category;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidCategory;

public class CategoryDTO extends DTO<Category> {

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

}
