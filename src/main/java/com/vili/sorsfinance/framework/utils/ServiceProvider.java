package com.vili.sorsfinance.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.exceptions.custom.ComponentNotFoundException;
import com.vili.sorsfinance.framework.exceptions.custom.MissingAnnotationException;
import com.vili.sorsfinance.framework.services.IService;

public interface ServiceProvider {

	default IService getService() {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<? extends IEntity> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			
			if (entityClass.isAnnotationPresent(ServiceRef.class)) {
				Class<? extends IService> serviceClass = entityClass.getAnnotation(ServiceRef.class).value();
				String aux = serviceClass.getSimpleName();
				String bean = aux.substring(0, 1).toLowerCase() + aux.substring(1);
				return getBean(bean);
			} else {
				throw new MissingAnnotationException("Class '" + entityClass.getSimpleName() + "' not annotaded with @ServiceRef");
			}
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}
	}
	
	public static IService getService(Class<?> type) {
			if (type.isAnnotationPresent(ServiceRef.class)) {
				Class<? extends IService> serviceClass = type.getAnnotation(ServiceRef.class).value();
				String aux = serviceClass.getSimpleName();
				String bean = aux.substring(0, 1).toLowerCase() + aux.substring(1);
				return getBean(bean);
			} else {
				throw new MissingAnnotationException("Class '" + type.getSimpleName() + "' not annotaded with @ServiceRef");
			}
	}

	private static IService getBean(String name) {
		try {
			ApplicationContext context = ContextProvider.getApplicationContext();
			return (IService) context.getBean(name);
		} catch (BeansException e) {
			throw new ComponentNotFoundException("Component '" + name + "' not found");
		}
    }
}
