package com.vili.sorsfinance.framework;

public interface IDataTransferObject {

	Long getId();
	
	void setId(Long id);
	
	boolean isActive();
	
	void setActive(boolean active);

	default DTOType getMethod() {
		if (getId() == null) {
			return DTOType.INSERT;
		} else {
			return DTOType.UPDATE;
		}
	}
	
	IEntity toEntity();
}
