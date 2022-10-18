package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.validation.constraints.ValidBranch;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidBranch
public class BranchDTO extends DataTransferObject {

	private String name;

	public BranchDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Branch toEntity() {
		return new Branch(getId(), getName());
	}
}
