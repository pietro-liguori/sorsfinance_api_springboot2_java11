package com.vili.sorsfinance.api.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vili.sorsfinance.api.validation.CardValidator;

@Documented
@Constraint(validatedBy = CardValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCard {
	String message() default "Card validation error";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
