package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.validation.constraints.ValidBranch;
import com.vili.sorsfinance.framework.DataTransferObject;

public class BranchDTO extends DataTransferObject {

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

	@Override
	public Branch toEntity() {
		return new Branch(getId(), getName());
	}
}
