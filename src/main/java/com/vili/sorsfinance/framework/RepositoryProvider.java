package com.vili.sorsfinance.framework;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.vili.sorsfinance.framework.exceptions.ComponentNotFoundException;
import com.vili.sorsfinance.framework.interfaces.IEntity;
import com.vili.sorsfinance.framework.interfaces.IRepository;

public class RepositoryProvider {

	@SuppressWarnings("unchecked")
	public static IRepository<IEntity> getRepository(String name) {
		try {
			ApplicationContext context = ContextProvider.getApplicationContext();
			return (IRepository<IEntity>) context.getBean(name);
		} catch (BeansException e) {
			throw new ComponentNotFoundException("Component '" + name + "' not found");
		}
    }
}