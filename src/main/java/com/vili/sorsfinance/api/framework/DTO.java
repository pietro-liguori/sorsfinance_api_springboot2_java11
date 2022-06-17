package com.vili.sorsfinance.api.framework;

public abstract class DTO<T extends BusEntity> {

	private Long id;
	
	public DTO() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public DTOType getMethod() {
		if (getId() == null) {
			return DTOType.INSERT;
		} else {
			return DTOType.UPDATE;
		}
	}
}
