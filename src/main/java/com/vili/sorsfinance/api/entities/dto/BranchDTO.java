package com.vili.sorsfinance.api.entities.dto;

import com.vili.sorsfinance.api.entities.Branch;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidBranch;

public class BranchDTO extends DTO<Branch> {

	@ValidBranch
	private String name;

	public BranchDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public BranchDTO setName(String name) {
		this.name = name;
		return this;
	}

}
