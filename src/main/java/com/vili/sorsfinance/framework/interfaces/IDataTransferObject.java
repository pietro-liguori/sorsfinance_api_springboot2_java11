package com.vili.sorsfinance.framework.interfaces;

import com.vili.sorsfinance.framework.enums.DTOType;

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
