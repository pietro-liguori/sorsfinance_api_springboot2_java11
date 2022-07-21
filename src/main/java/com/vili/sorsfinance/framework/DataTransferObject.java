package com.vili.sorsfinance.framework;

import com.vili.sorsfinance.framework.interfaces.IDataTransferObject;

public abstract class DataTransferObject implements IDataTransferObject {

	private Long id;
	private boolean active;
	
	public DataTransferObject() {
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
		
	}
}