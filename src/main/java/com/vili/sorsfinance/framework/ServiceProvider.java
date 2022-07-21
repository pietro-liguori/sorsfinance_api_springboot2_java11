package com.vili.sorsfinance.framework;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.vili.sorsfinance.framework.exceptions.ComponentNotFoundException;
import com.vili.sorsfinance.framework.interfaces.IService;

public class ServiceProvider {

	public static IService getService(String name) {
		try {
			ApplicationContext context = ContextProvider.getApplicationContext();
			return (IService) context.getBean(name);
		} catch (BeansException e) {
			throw new ComponentNotFoundException("Component '" + name + "' not found");
		}
    }
}