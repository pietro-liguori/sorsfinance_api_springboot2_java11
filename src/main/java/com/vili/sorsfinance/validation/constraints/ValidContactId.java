package com.vili.sorsfinance.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.validation.ContactIdValidator;

@Documented
@NotNull(message = "Must not be null")
@Constraint(validatedBy = ContactIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContactId {

	String message() default "Contact id validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
