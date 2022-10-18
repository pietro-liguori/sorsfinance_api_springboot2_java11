package com.vili.sorsfinance.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.exceptions.custom.ComponentNotFoundException;
import com.vili.sorsfinance.framework.exceptions.custom.MissingAnnotationException;
import com.vili.sorsfinance.framework.repositories.IRepository;

public interface RepositoryProvider {

	default IRepository<IEntity> getRepository() {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<?> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			
			if (entityClass.isAnnotationPresent(RepositoryRef.class)) {
				Class<? extends IRepository<?>> repositoryClass = entityClass.getAnnotation(RepositoryRef.class).value();
				String aux = repositoryClass.getSimpleName();
				String bean = aux.substring(0, 1).toLowerCase() + aux.substring(1);
				return getBean(bean);
			} else {
				throw new MissingAnnotationException("Class '" + entityClass.getSimpleName() + "' not annotaded with @RepositoryRef");
			}
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}
	}
	
	static IRepository<IEntity> get(Class<?> type) {
		if (type.isAnnotationPresent(RepositoryRef.class)) {
			Class<? extends IRepository<?>> repositoryClass = type.getAnnotation(RepositoryRef.class).value();
			String aux = repositoryClass.getSimpleName();
			String bean = aux.substring(0, 1).toLowerCase() + aux.substring(1);
			return getBean(bean);
		} else {
			throw new MissingAnnotationException("Class '" + type.getSimpleName() + "' not annotaded with @RepositoryRef");
		}
	}
	
	@SuppressWarnings("unchecked")
	private static IRepository<IEntity> getBean(String name) {
		try {
			ApplicationContext context = ContextProvider.getApplicationContext();
			return (IRepository<IEntity>) context.getBean(name);
		} catch (BeansException e) {
			throw new ComponentNotFoundException("Component '" + name + "' not found");
		}
    }
}
