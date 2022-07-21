package com.vili.sorsfinance.api.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.api.validation.TransactionValidator;

@Documented
@NotNull(message = "Must not be null")
@Constraint(validatedBy = TransactionValidator.class)
@Target({ ElementType.TYPE, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTransaction {
	String message() default "Transaction validation error";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
