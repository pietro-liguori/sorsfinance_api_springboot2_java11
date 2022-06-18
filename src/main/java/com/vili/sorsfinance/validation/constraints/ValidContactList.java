package com.vili.sorsfinance.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;

import com.vili.sorsfinance.validation.ContactListValidator;

@Documented
@NotEmpty(message = "Must reference at least one contact")
@Constraint(validatedBy = ContactListValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContactList {

	String message() default "Contact id validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
