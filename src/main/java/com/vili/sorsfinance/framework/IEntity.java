package com.vili.sorsfinance.framework;

import java.io.Serializable;
import java.util.Date;

public interface IEntity extends Serializable {

	Long getId();
	
	void setId(Long id);
	
	Class<?> getDomainClass();
	
	void setDomainClass(Class<?> type);
	
	Date getCreatedAt();
	
	void setCreatedAt(Date date);
	
	Date getUpdatedAt();
	
	void setUpdatedAt(Date date);
	
	boolean isActive();
	
	void setActive(boolean active);
}
