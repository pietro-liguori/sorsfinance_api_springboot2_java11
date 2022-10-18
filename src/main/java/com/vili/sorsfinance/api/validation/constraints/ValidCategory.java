package com.vili.sorsfinance.api.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vili.sorsfinance.api.validation.CategoryValidator;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {

	String message() default "Category validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
