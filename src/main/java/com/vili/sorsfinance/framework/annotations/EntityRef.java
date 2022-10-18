package com.vili.sorsfinance.framework.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.vili.sorsfinance.framework.IEntity;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface EntityRef {
	
	Class<? extends IEntity> value();
}
