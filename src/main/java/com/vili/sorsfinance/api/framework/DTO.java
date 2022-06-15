package com.vili.sorsfinance.api.framework;

public abstract class DTO<T extends BusEntity> {

	public abstract Long getId();
	
	public DTOType getMethod() {
		if (getId() == null) {
			return DTOType.INSERT;
		} else {
			return DTOType.UPDATE;
		}
	}
}
